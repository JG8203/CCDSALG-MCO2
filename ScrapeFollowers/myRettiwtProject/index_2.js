// Import the Rettiwt package
const Rettiwt = require('rettiwt-api').Rettiwt;

// Load environment variables from the .env file
require('dotenv').config();

// Create a new instance of Rettiwt with your tokens
const rettiwt = new Rettiwt({
  kdt: process.env.KDT,
  twid: process.env.TWID,
  ct0: process.env.CT0,
  auth_token: process.env.AUTH_TOKEN
});

// Create a user service
const userService = rettiwt.users;


const fs = require('fs');

// BFS approach to traverse the follower network
async function getAdjacencyList(username, depth) {
    let userIdToNumber = new Map();
    let numberToUsername = new Map();
    let adjacencyList = [];
    let queue = [];
    let nextQueue = [];
    let number = 1;

    // Get the user details and start the BFS with this user
    const userDetails = await userService.getUserDetails(username);
    queue.push(userDetails.id);
    userIdToNumber.set(userDetails.id, number);
    numberToUsername.set(number, userDetails.userName);
    number++;

    // BFS loop
    for (let i = 0; i < depth; i++) {
        for (let userId of queue) {
            try {
                // Get the followers of this user
                const followersResult = await userService.getUserFollowers(userId, 40);
                const followers = followersResult.list;
                for (let follower of followers) {
                    // Assign a unique number to each follower
                    if (!userIdToNumber.has(follower.id)) {
                        userIdToNumber.set(follower.id, number);
                        numberToUsername.set(number, follower.userName);
                        number++;
                    }
                    // Add an edge to the adjacency list
                    adjacencyList.push([userIdToNumber.get(userId), userIdToNumber.get(follower.id)]);
                    // Add the follower to the next queue
                    nextQueue.push(follower.id);
                }
            } catch (error) {
                console.error(error);
            }
        }
        // Move to the next depth level
        queue = nextQueue;
        nextQueue = [];
    }

    // Write the adjacency list to a text file
    let adjacencyListText = adjacencyList.map(edge => edge.join(' ')).join('\n');
    fs.writeFileSync('adjacency_list.txt', adjacencyListText);

    // Write the lookup table to a text file
    let lookupTableText = Array.from(numberToUsername.entries()).map(([number, username]) => `${number} ${username}`).join('\n');
    fs.writeFileSync('lookup_table.txt', lookupTableText);
}

// Usage
getAdjacencyList('gilo_joshua', 3).catch(console.error);

