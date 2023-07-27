// Import the Rettiwt package
const Rettiwt = require('rettiwt-api').Rettiwt;

// Load environment variables from the .env file
require('dotenv').config();

// Create a new instance of Rettiwt
const rettiwt = new Rettiwt();

// Use the login method to get your tokens
rettiwt.account.login(process.env.EMAIL, process.env.USERNAME, process.env.PASSWORD)
  .then(response => {
    // Print out the response (this contains your tokens)
    console.log(response);
  })
  .catch(err => {
    // If something went wrong, print out the error
    console.error(err);
  });
