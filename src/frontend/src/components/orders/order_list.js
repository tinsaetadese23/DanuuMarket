
// src/components/Order/OrderList.js

import React, { useEffect, useState } from "react";
import axios from "axios";

function OrderList() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/orders/get")
            .then((response) => {
                setOrders(response.data);
            })
            .catch((error) => {
                console.error("There was an error fetching orders!", error);
            });
    }, []);

    return (
        <div>
            <h2>Order List</h2>
            <ul>
                {orders.length === 0 ? (
                    <p>No orders available.</p>
                ) : (
                    orders.map((order) => (
                        <li key={order.id}>
                            <h3>Order ID: {order.id}</h3>
                            <p>Quantity: {order.quantity}</p>
                            <p>Total Price: ${order.total_price}</p>
                        </li>
                    ))
                )}
            </ul>
        </div>
    );
}

export default OrderList;
