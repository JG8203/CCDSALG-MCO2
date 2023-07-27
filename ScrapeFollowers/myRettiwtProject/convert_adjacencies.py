import json

# Load adjacency list from JSON file
with open('adjacency_list.json', 'r') as f:
    adjacency_list = json.load(f)

# Write results to a text file
with open('adjacency_list.txt', 'w') as f:
    # Write number of nodes and relationships
    f.write(f"{len(adjacency_list)} {sum(len(v) for v in adjacency_list.values())}\n")

    # Write adjacency list
    for key, values in adjacency_list.items():
        for value in values:
            f.write(f"{key} {value}\n")
