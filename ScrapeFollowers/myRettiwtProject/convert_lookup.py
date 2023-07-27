
import json

# Load lookup table from JSON file
with open('lookup_table.json', 'r') as f:
    lookup_table = json.load(f)

# Write lookup table to a text file
with open('lookup_table.txt', 'w') as f:
    for key, value in lookup_table.items():
        f.write(f"{key} {value}\n")
