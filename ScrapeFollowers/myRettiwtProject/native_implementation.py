
import tweepy
import os
import json

# Load environment variables
from dotenv import load_dotenv
load_dotenv("keys.env")

# Authenticate to Twitter
client = tweepy.Client(bearer_token=os.getenv("BEARER_TOKEN"))

def get_followers(user, depth, current_depth, adjacency_list, lookup_table, counter):
    if current_depth > depth:
        return counter

    followers = client.get_users_following(user.id, max_results=5)

    adjacency_list[counter] = [counter + i for i in range(1, len(followers.data) + 1)]
    lookup_table[counter] = user.username

    for i, follower in enumerate(followers.data, start=1):
        lookup_table[counter + i] = follower.username

    counter += len(followers.data)

    for follower in followers.data:
        counter = get_followers(follower, depth, current_depth + 1, adjacency_list, lookup_table, counter)

    return counter

def save_data(adjacency_list, lookup_table):
    adjacency_list_filename = 'adjacency_list.json'
    lookup_table_filename = 'lookup_table.json'

    counter = 1
    while os.path.isfile(adjacency_list_filename):
        adjacency_list_filename = f"adjacency_list_{counter}.json"
        counter += 1

    counter = 1
    while os.path.isfile(lookup_table_filename):
        lookup_table_filename = f"lookup_table_{counter}.json"
        counter += 1

    with open(adjacency_list_filename, 'w') as f:
        json.dump(adjacency_list, f)

    with open(lookup_table_filename, 'w') as f:
        json.dump(lookup_table, f)

def scrape_twitter(username, depth):
    adjacency_list = {}
    lookup_table = {}
    counter = 1

    user = client.get_user(username=username) 
    counter = get_followers(user.data, depth, 1, adjacency_list, lookup_table, counter)

    save_data(adjacency_list, lookup_table)

    return adjacency_list, lookup_table

# Call the function
adjacency_list, lookup_table = scrape_twitter("gilo_joshua", 2)

# Print results
print("Adjacency List:", adjacency_list)
print("Lookup Table:", lookup_table)

