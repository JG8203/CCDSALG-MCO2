
import graphviz
from collections import defaultdict
from collections import deque

def bfs(graph, start, end):
    # Initialize all nodes to white, distance to infinity, and no predecessor
    colors = {node: 'white' for node in graph}
    distances = {node: float('inf') for node in graph}
    predecessors = {node: None for node in graph}

    # The start node is gray, distance is 0, and it has no predecessor
    colors[start] = 'gray'
    distances[start] = 0

    # Create a queue for BFS and enqueue the start node
    queue = deque([start])

    # List to hold the state of the graph and queue at each step
    states = []

    while queue:
        # Dequeue a node from the queue
        node = queue.popleft()

        # Get the current state of the graph and queue
        state = {
            'node': node,
            'colors': colors.copy(),
            'distances': distances.copy(),
            'predecessors': predecessors.copy(),
            'queue': list(queue)
        }
        states.append(state)

        # If this is the end node, we're done
        if node == end:
            break

        # Get all adjacent nodes
        for adjacent in graph[node]:
            # If the adjacent node is white, it's unvisited
            if colors[adjacent] == 'white':
                # Mark it as gray (discovered but not finished), update its distance and predecessor
                colors[adjacent] = 'gray'
                distances[adjacent] = distances[node] + 1
                predecessors[adjacent] = node

                # Enqueue the adjacent node
                queue.append(adjacent)

        # Mark the node as black (finished)
        colors[node] = 'black'
        
    return states

def read_graph(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()

    # The first line contains the number of nodes and edges
    num_nodes, num_edges = map(int, lines[0].split())

    # Create the graph
    graph = defaultdict(list)
    for line in lines[1:]:
        u, v = map(int, line.split())
        graph[u].append(v)
        graph[v].append(u)  # assuming this is an undirected graph

    return graph

def create_graphviz_color_simplified(states, idx, graph_data):
    # Create a new directed graph
    g = graphviz.Digraph()

    # Get the current state
    state = states[idx]
    colors = state['colors']
    distances = state['distances']

    # Map the colors to Graphviz colors
    color_map = {
        'white': 'white',
        'gray': 'gray',
        'black': 'black'
    }

    # Add all nodes to the graph
    for node in colors:
        color = colors[node]
        distance = distances[node] if distances[node] != float('inf') else '∞'
        label = f'{distance}'
        g.node(str(node), label=label, style='filled', fillcolor=color_map[color])

    # Add all edges to the graph
    for u, v in graph_data:
        g.edge(str(u), str(v))

    return g

# Read the graph from the file
graph_path = "../data/small_multiple.txt"  # Replace with the path to your file
graph = read_graph(graph_path)

# Perform BFS
bfs_states = bfs(graph, 1, 4)  # Replace 1 and 4 with your start and end nodes

# Create the Graphviz graphs for each state
graphviz_graphs = [create_graphviz_color_simplified(bfs_states, i, graph_data=graph.items()) for i in range(len(bfs_states))]

# Print the DOT source code for each graph
for i, g in enumerate(graphviz_graphs, start=1):
    print(f'Graph state {i}:\n{g.source}\n')

# Save the Graphviz graphs as SVG files
for i, g in enumerate(graphviz_graphs, start=1):
    g.format = 'svg'
    g.render(filename=f'graph_state_{i}')  # Save the SVG file

