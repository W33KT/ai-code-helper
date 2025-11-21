# Common Developer Interview Questions

The following list covers high-frequency technical interview questions. For more comprehensive practice, we recommend:
*  ([https://www.techinterviewhandbook.org/](https://www.techinterviewhandbook.org/))
*  ([https://leetcode.com/discuss/interview-question](https://leetcode.com/discuss/interview-question))

## Java Core Basics

*   What are the 8 primitive data types in Java? What are their corresponding wrapper classes?
*   What is the difference between `==` and `.equals()`?
*   Compare `String`, `StringBuilder`, and `StringBuffer`. When would you use each?
*   Explain Java's Exception Handling hierarchy. What is the difference between **Checked** and **Unchecked** exceptions?
*   What are the differences between `final`, `finally`, and `finalize`?
*   Explain the difference between Method Overloading and Method Overriding.
*   What are the key differences between an **Abstract Class** and an **Interface**? In what scenarios would you choose one over the other?
*   What are Java Generics? Explain the concept of **Type Erasure**.
*   What is the Reflection API? Can you give a real-world use case (e.g., in frameworks)?
*   Describe the **JVM Runtime Data Areas** (Memory Structure). What is the difference between the **Stack** and the **Heap**?
*   How does Garbage Collection (GC) work in Java? Name a few common GC algorithms (e.g., Mark-and-Sweep, G1).
*   What are the different ways to create a Thread in Java? Pros and cons of each?
*   What is the difference between the `synchronized` keyword and the `volatile` keyword?
*   What is `ThreadLocal`? How is it used, and what is the risk of memory leaks?
*   Explain the different types of Locks in Java. What is the difference between **Optimistic Locking** and **Pessimistic Locking**?

## Databases (SQL & MySQL)

*   What is the **Leftmost Prefix Rule** (Composite Index) in MySQL?
*   Explain the transaction isolation phenomena: **Dirty Read**, **Non-repeatable Read**, and **Phantom Read**.
*   Compare MySQL Storage Engines: **InnoDB** vs. **MyISAM**.
*   What is a **Covering Index**?
*   What are the different types of Indexes in MySQL (B-Tree, Hash, Full-text)?
*   What is **Index Condition Pushdown (ICP)**?
*   What is the difference between a **Clustered Index** and a **Non-Clustered (Secondary) Index** in InnoDB?
*   Explain the concept of **"Secondary Index Lookup"** (often called "Back to Table" or *回表* in some contexts). How can you avoid it?
*   Does using an index always guarantee faster performance? How do you analyze query performance (e.g., `EXPLAIN` command)?
*   Is it better to have as many indexes as possible? Why or why not?
*   Describe the step-by-step process of a data query in a MySQL **B+ Tree**.
*   Why does MySQL use **B+ Trees** for indexing instead of Hash Maps or Red-Black Trees?

## Redis (Caching)

*   What are the main data types in Redis (String, List, Set, Hash, Sorted Set)? Provide a use case for each.
*   Explain Redis persistence mechanisms: **RDB** (Snapshotting) vs. **AOF** (Append Only File).
*   Explain the following caching problems and their solutions:
    *   **Cache Penetration** (Data exists in neither cache nor DB).
    *   **Cache Breakdown/Stampede** (Hot key expires).
    *   **Cache Avalanche** (Massive simultaneous expirations).
*   What are the Redis deployment modes? Compare **Master-Slave**, **Sentinel**, and **Cluster**.
*   What are the Redis key expiration strategies? How does the memory eviction mechanism work (LRU/LFU)?
*   How do you implement a **Distributed Lock** using Redis? What are the potential pitfalls (e.g., Redlock algorithm)?
*   Does Redis support ACID transactions? Explain the `MULTI`, `EXEC`, and `WATCH` commands.
*   What is the Redis Pub/Sub model? What are its limitations compared to a dedicated Message Queue?
*   Why is Redis so fast (Single-threaded event loop, memory-based)?
*   What is the purpose of Lua scripts in Redis? How do they ensure atomicity?

## Message Queues (Kafka & RabbitMQ)

*   *Architecture:* Why is **Apache Kafka** moving away from Zookeeper (KRaft mode)? (Replacing the RocketMQ-specific NameServer question).
*   How can you implement a **Delayed Queue** in RabbitMQ?
*   When does a message enter the **Dead Letter Exchange (DLX)** in RabbitMQ?
*   Explain the **Partitioning** mechanism in Kafka. How does it guarantee message ordering?
*   How does Kafka ensure **Zero Message Loss**? Discuss the configurations for Producer (`acks`), Broker (Replication), and Consumer (Offset commits).
*   Compare **Kafka** vs. **RabbitMQ**. What are their respective use cases (Throughput vs. Latency/Routing)?
*   Describe Kafka's storage internals. What is the role of **Log Segments** and **Indexes**? (Replacing RocketMQ CommitLog).
*   How do you handle **Idempotency** in message consumption? How do you solve the "Duplicate Consumption" problem?
*   How are **Transactional Messages** implemented in distributed systems?
*   How do you choose the right Message Queue? What factors (Latency, Throughput, Persistence, Ordering) should you consider?
*   What is the **High Watermark (HW)** in Kafka? How does it relate to data consistency?
*   How do you handle message retries? Explain the concept of **Retry Topics** and **Dead Letter Queues (DLQ)**.