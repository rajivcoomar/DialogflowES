const { JWT } = require('google-auth-library');
const path = require('path'); // Node.js built-in module for working with file paths

const serviceAccountKeyPath = path.resolve(__dirname, 'servicekey.json'); // Replace with the path to your service account JSON file
const scopes = ['https://www.googleapis.com/auth/cloud-platform']; // Adjust the scopes as needed

// Create a new JWT client with the service account credentials
const client = new JWT({
  keyFile: serviceAccountKeyPath,
  scopes,
});

// Generate an authentication token
client.authorize((err, tokens) => {
  if (err) {
    console.error('Error authenticating:', err);
    return;
  }

  console.log('Authentication token:', tokens);
  // You can now use the 'tokens' object to make authenticated API requests.
});
