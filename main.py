import requests
from datetime import datetime

URL = "http://localhost:8080/orders"  # order-service endpoint

orders = [
    {"customerName": "Alice", "status": "unprocessed", "orderDate": str(datetime.now()), "orderLines": [
        {"item": "Item1", "quantity": 2, "price": 50},
        {"item": "Item2", "quantity": 1, "price": 40},
        {"item": "Item3", "quantity": 3, "price": 20},
    ]},
    {"customerName": "Bob", "status": "unprocessed", "orderDate": str(datetime.now()), "orderLines": [
        {"item": "Item4", "quantity": 1, "price": 100},
        {"item": "Item5", "quantity": 2, "price": 30},
    ]},
]

for order in orders:
    response = requests.post(URL, json=order)
    if response.status_code == 200 or response.status_code == 201:
        print("✅ Order sent successfully!")
        print(response.json())
    else:
        print("❌ Failed to send order:", response.status_code, response.text)