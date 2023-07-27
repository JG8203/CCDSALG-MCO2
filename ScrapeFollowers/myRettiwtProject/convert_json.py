
import json

# Load adjacency list from JSON file
with open('adjacency_list.json', 'r') as f:
    adjacency_list = json.load(f)

# Print number of nodes and relationships
print(len(adjacency_list), sum(len(v) for v in adjacency_list.values()))

# Print adjacency list
for key, values in adjacency_list.items():
    for value in values:
        print(key, value)
