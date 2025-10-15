# Supermarket Management System - Hash Table Module

This is a university assignment for the **CME 2201** course, focusing on data structures. The project involves developing a high-performance module for a supermarket management system using a custom-built hash table in Java.

**Authors:**
* Yasamin Valishariatpanahi
* Sara Mahyanbakhshayesh

---

## About The Project

[cite_start]The primary goal of this project is to build an efficient system for the rapid retrieval of customer purchase histories from a large dataset[cite: 4106]. [cite_start]The system uses a custom hash table implementation to store and manage 500,000 purchase transactions from 50,000 unique customers[cite: 4108]. [cite_start]The customer's 128-bit UUID serves as the key for all hash table operations[cite: 4110, 4114].

[cite_start]The hash table stores each customer's transactions in a sorted list, ordered in reverse chronological order for quick access to the latest purchases[cite: 4121, 4123].



### Core Functionalities

Our hash table implementation supports the following essential methods:
* `put(Key k, Value v)`: Adds a new transaction to a customer's record. [cite_start]If the customer doesn't exist, it creates a new entry[cite: 4127].
* [cite_start]`get(Key k)`: Searches for and returns all transactions for a given customer UUID[cite: 4130, 4131].
* [cite_start]`remove(Key k)`: Deletes a customer and all their associated transaction data from the hash table[cite: 4135].
* [cite_start]`resize(int capacity)`: Automatically doubles the hash table's size when the load factor reaches a specified threshold (50% or 80%) to maintain performance[cite: 4137, 4167].

---

## Technical Implementation

To analyze efficiency, the project was implemented with different configurations as required by the assignment.

### Hash Functions
[cite_start]Two distinct hash functions were implemented to convert customer UUIDs into hash codes[cite: 4141]:
1.  [cite_start]**Simple Summation Function (SSF)** [cite: 4142]
2.  [cite_start]**Polynomial Accumulation Function (PAF)** [cite: 4145]

### Collision Handling
[cite_start]Two different collision resolution techniques were implemented[cite: 4167]:
1.  [cite_start]**Linear Probing (LP)**: Places a colliding item in the next available slot[cite: 4156].
2.  [cite_start]**Double Hashing (DH)**: Uses a secondary hash function to determine the next slot to check[cite: 4158].

---

## Performance Analysis

A major component of this project was monitoring and comparing the performance of the different implementations. [cite_start]We measured the total collision count, the time taken to load the dataset (indexing time), and the average, minimum, and maximum search times using a provided list of 1,000 customer UUIDs[cite: 4168, 4169]. [cite_start]These tests were conducted for each combination of hash function, collision handling technique, and load factor (50% and 80%)[cite: 4167].

---

## How to Run

To run this project:
1.  Ensure you have a Java Development Kit (JDK) installed.
2.  Compile all `.java` files.
3.  Run the main executable class. The program will load the `supermarket_dataset_50K.csv` file into the hash table and perform the required performance tests.
