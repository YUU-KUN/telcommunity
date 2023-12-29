'use strict';

var stompClient = null;
var username = null;

function connect() {
    // You may want to prompt the user for a username before connecting
    // username = prompt("Enter your username:");

    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.register",
        {},
        JSON.stringify({ sender: username, type: 'JOIN' })
    );

    console.log('Connected to WebSocket');
}

function onError(error) {
    console.error('Error connecting to WebSocket:', error);
    alert('Error connecting to WebSocket. Please refresh the page and try again.');
}

function send() {
    var messageInput = document.querySelector('#message');
    var messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    // Process and display the received message in your UI
    console.log('Received message:', message);
    // Add your logic to display the message in the UI
}

// You may want to call the connect function when the page loads or when the user logs in
// Example: connect();

// Add event listeners for sending messages
var messageForm = document.querySelector('#messageForm');
messageForm.addEventListener('submit', function (event) {
    send();
    event.preventDefault();
}, true);
