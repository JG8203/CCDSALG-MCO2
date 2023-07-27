
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
from tqdm import tqdm
import undetected_chromedriver as uc
import os
import time
import json

# Load environment variables
from dotenv import load_dotenv
load_dotenv()

TWITTER_USERNAME = os.getenv("TWITTER_USERNAME")
TWITTER_PASSWORD = os.getenv("TWITTER_PASSWORD")

BRAVE_PATH = '/Applications/Brave Browser.app/Contents/MacOS/Brave Browser'
BASE_URL = 'https://twitter.com/'

options = Options()
options.binary_location = BRAVE_PATH

def get_webdriver(options):
    return uc.Chrome(options=options)

def login_twitter(driver, username, password):
    driver.get('https://twitter.com/login')
    time.sleep(2)  # Let the page load

    ActionChains(driver)\
        .move_to_element(driver.find_element(By.NAME, 'text'))\
        .click().send_keys(username)\
        .send_keys(Keys.RETURN)\
        .perform()

    time.sleep(2)  # Let the page load
    
    ActionChains(driver)\
        .move_to_element(driver.find_element(By.NAME, 'password'))\
        .click().send_keys(password)\
        .send_keys(Keys.RETURN)\
        .perform()

    time.sleep(2)  # Let the page load

def get_followers(driver, username, depth, current_depth, adjacency_list, lookup_table, counter):
    if current_depth > depth:
        return counter

    driver.get(BASE_URL + username + '/followers')
    wait = WebDriverWait(driver, 10)  # wait up to 10 seconds for the elements to become available

    followers = set()
    
    start_time = time.time()  # start time of the first operation

    try:
        # Scroll down to load followers
        for i in tqdm(range(6, 11), desc='Loading followers'):  # Modify this range based on the number of followers you want to scrape
            follower_xpath = f"/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div/div/div[{i}]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/a/div/div"
            follower_element = wait.until(EC.presence_of_element_located((By.XPATH, follower_xpath)))
            follower = follower_element.text
            if follower:
                followers.add(follower[1:])  # Remove the '@' from the username

            if i == 1:
                end_time = time.time()  # end time of the first operation
                total_time_for_all_operations = (end_time - start_time) * depth * len(range(1, 6))
                print(f"Estimated total time: {total_time_for_all_operations} seconds")

    except Exception as e:
        print(str(e))
    adjacency_list[counter] = [counter + i for i in range(1, len(followers) + 1)]
    lookup_table[counter] = username

    for i, follower in enumerate(followers, start=1):
        lookup_table[counter + i] = follower

    counter += len(followers)

    for follower in tqdm(followers):
        counter = get_followers(driver, follower, depth, current_depth + 1, adjacency_list, lookup_table, counter)

    return counter

def save_data(adjacency_list, lookup_table):
    with open('adjacency_list.json', 'w') as f:
        json.dump(adjacency_list, f)

    with open('lookup_table.json', 'w') as f:
        json.dump(lookup_table, f)

def scrape_twitter(username, password, target_username, depth):
    driver = get_webdriver(options)
    login_twitter(driver, username, password)

    adjacency_list = {}
    lookup_table = {}
    counter = 1

    counter = get_followers(driver, target_username, depth, 1, adjacency_list, lookup_table, counter)

    save_data(adjacency_list, lookup_table)

    driver.quit()

    return adjacency_list, lookup_table

# Call the function
adjacency_list, lookup_table = scrape_twitter(TWITTER_USERNAME, TWITTER_PASSWORD, TWITTER_USERNAME, 5)

# Print results
print("Adjacency List:", adjacency_list)
print("Lookup Table:", lookup_table)

