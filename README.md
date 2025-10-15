# Supermarket Management System - Hash Table Module

This is a university assignment for the **CME 2201** course, focusing on data structures. The project involves developing a high-performance module for a supermarket management system using a custom-built hash table in Java.

**Authors:**
* Yasamin Valishariatpanahi
* Sara Mahyanbakhshayesh

---

## About The Project

The primary goal of this project is to build an efficient system for the rapid retrieval of customer purchase histories from a large dataset. The system uses a custom hash table implementation to store and manage 500,000 purchase transactions from 50,000 unique customers. The customer's 128-bit UUID serves as the key for all hash table operations.

The hash table stores each customer's transactions in a sorted list, ordered in reverse chronological order for quick access to the latest purchases.



### Core Functionalities

Our hash table implementation supports the following essential methods:
* `put(Key k, Value v)`: Adds a new transaction to a customer's record. If the customer doesn't exist, it creates a new entry.
* `get(Key k)`: Searches for and returns all transactions for a given customer UUID.
* `remove(Key k)`: Deletes a customer and all their associated transaction data from the hash table.
* `resize(int capacity)`: Automatically doubles the hash table's size when the load factor reaches a specified threshold (50% or 80%) to maintain performance.

---

## Technical Implementation

To analyze efficiency, the project was implemented with different configurations as required by the assignment.

### Hash Functions
Two distinct hash functions were implemented to convert customer UUIDs into hash codes:
1.  **Simple Summation Function (SSF)**
2.  **Polynomial Accumulation Function (PAF)**

### Collision Handling
Two different collision resolution techniques were implemented:
1.  **Linear Probing (LP)**: Places a colliding item in the next available slot.
2.  **Double Hashing (DH)**: Uses a secondary hash function to determine the next slot to check.

---

## Performance Analysis

A major component of this project was monitoring and comparing the performance of the different implementations. We measured the total collision count, the time taken to load the dataset (indexing time), and the average, minimum, and maximum search times using a provided list of 1,000 customer UUIDs. These tests were conducted for each combination of hash function, collision handling technique, and load factor (50% and 80%).

---

## How to Run

To run this project:
1.  Ensure you have a Java Development Kit (JDK) installed.
2.  Compile all `.java` files.
3.  Run the main executable class. The program will load the `supermarket_dataset_50K.csv` file into the hash table and perform the required performance tests.
