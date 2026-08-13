# 🌐 Distributed Systems & Network Programming Labs

Welcome to the comprehensive repository for the **Distributed Systems** course. This repository showcases practical laboratory implementations and key projects built with **Java (JDK 11+)**, covering core distributed concepts such as **Socket Programming (TCP/UDP)**, **Multithreading**, **3-Tier Architectures**, and **Java Remote Method Invocation (RMI)**.

---

## 🛠️ Tech Stack & Key Concepts

* **Programming Language:** Java (JDK 11+)
* **Networking APIs:** `java.net.*` (`Socket`, `ServerSocket`, `DatagramSocket`, `DatagramPacket`, `InetAddress`), `java.rmi.*`
* **Concurrency:** Java Multithreading (`Thread`, `Runnable`), Non-blocking `CompletableFuture`
* **Architectural Patterns:** Client-Server, Multi-Threaded Concurrency, Distributed 3-Tier Processing, Asynchronous Callbacks
* **Build Management:** Apache Maven (`pom.xml`)

---

## 📑 Quick Navigation & Course Content

| Section / Folder | Protocol / Tech | Key Objective & Description |
| :--- | :--- | :--- |
| [`lab01-tcp-basic-echo`](./lab01-tcp-basic-echo) | **TCP Sockets** | Fundamental TCP stream communication for uppercase text echo. |
| [`lab02-udp-basic-echo`](./lab02-udp-basic-echo) | **UDP Sockets** | Lightweight datagram-based socket echo with clean byte-buffer handling. |
| [`lab03-udp-dns-resolver`](./lab03-udp-dns-resolver) | **UDP + InetAddress** | Real-time domain name to IP resolution over UDP sockets. |
| [`lab04-tcp-multithreaded-chat`](./lab04-tcp-multithreaded-chat) | **TCP + Multithreading** | Multi-client interactive chat server using dedicated handler threads. |
| [`lab05-3tier-word-count`](./lab05-3tier-word-count) | **Distributed 3-Tier** | Processing tier fetching data streams from a Data tier to count word frequencies. |
| [`lab06-rmi-addition-calculator`](./lab06-rmi-addition-calculator) | **Java RMI** | Fundamental remote procedure calls via RMI Registry for math operations. |
| [`lab07-rmi-async-time-service`](./lab07-rmi-async-time-service) | **Multi-RMI + Threads** | Orchestrating multiple RMI servers; comparing blocking Sync vs Async threads. |
| [`assignment01-hierarchical-dns`](./assignment01-hierarchical-dns) | **UDP Caching Network** | 3-Layer Hierarchical DNS System (Local Server + Cache, Root, Auth). |
| [`assignment02-async-rmi-grading`](./assignment02-async-rmi-grading) | **Async RMI** | Non-blocking Academic Grading Evaluator using `CompletableFuture`. |

---

## 🔬 Detailed Lab Breakdowns

### 📍 Lab 1: Basic TCP Echo Server (`lab01-tcp-basic-echo`)
* **Objective:** Establish a fundamental stream-based TCP socket connection between client and server.
* **Mechanism:** 
  * `TCPServer` listens on port `9876` using `ServerSocket`.
  * `TCPClient` connects via `Socket` and streams text input via `DataOutputStream`.
  * The server reads the stream, converts text to uppercase, prints connection metadata (Client IP/Port), and echoes the result back.

---

### 📍 Lab 2: Basic UDP Echo Server (`lab02-udp-basic-echo`)
* **Objective:** Implement connectionless datagram communication using UDP.
* **Mechanism:**
  * Uses `DatagramSocket` and `DatagramPacket` on port `9876`.
  * Handles byte-array payload trimming (`new String(data, 0, length)`) to avoid trailing buffer artifact issues during uppercase conversion.

---

### 📍 Lab 3: UDP DNS Resolver (`lab03-udp-dns-resolver`)
* **Objective:** Resolve domain names into IP addresses using network lookup primitives over UDP.
* **Mechanism:**
  * `UDPClient` prompts the user for a domain (e.g., `google.com`) and transmits it to port `5300`.
  * `UDPServer` receives the packet and invokes `InetAddress.getByName(host)` to fetch the real IP address, returning the result back to the client.

---

### 📍 Lab 4: Multi-Threaded TCP Interactive Chat (`lab04-tcp-multithreaded-chat`)
* **Objective:** Support multiple simultaneous TCP client connections without blocking the server listener.
* **Mechanism:**
  * `MultiThreadedServer` listens on port `8044`.
  * For every accepted connection (`serverSocket.accept()`), a new thread (`new Thread(...)`) is spawned to handle interactive bi-directional messaging with that specific client until the client sends `stop`.

---

### 📍 Lab 5: Distributed 3-Tier Word Frequency Architecture (`lab05-3tier-word-count`)
* **Objective:** Decouple data storage from business logic processing using a 3-tier distributed model.
* **Components:**
  1. **Client Tier (`Client.java`):** Prompts user for a search word and sends it to `ProcessServer` (Port 7777).
  2. **Processing Tier (`ProcessServer.java`):** Receives client query, connects to `DataServer` (Port 8888) in the background, streams text lines, calculates occurrences (`equalsIgnoreCase`), and returns the final count to the client.
  3. **Data Tier (`DataServer.java`):** Acts as a dedicated storage node streaming text data upon request.

---

### 📍 Lab 6: RMI Addition Calculator (`lab06-rmi-addition-calculator`)
* **Objective:** Implement Remote Method Invocation (RMI) for basic distributed function execution.
* **Mechanism:**
  * Defines `SumInterface` extending `Remote` and its implementation `SumImpl`.
  * `SumServer` binds the remote object to `LocateRegistry` on port `1900` under `"myapp"`.
  * `SumClient` looks up the remote interface reference via `Naming.lookup()` and executes `s.sum(a, b)` remotely.

---

### 📍 Lab 7: Multi-RMI Orchestration & Async Execution (`lab07-rmi-async-time-service`)
* **Objective:** Evaluate synchronous (blocking) vs asynchronous (non-blocking) multi-server invocation patterns.
* **Mechanism:**
  * Interacts with two distinct RMI servers: Sum Service (Port 1900) and Time Service with simulated delay (Port 2300).
  * `SyncClient` blocks execution while awaiting slow time responses.
  * `AsyncClient` executes the delayed time request in a dedicated background thread, allowing immediate math computation on the main thread.

---

## 🚀 Featured Assignments Architecture

### 🏆 Assignment 1: 3-Layer Hierarchical UDP DNS System (`assignment01-hierarchical-dns`)
Simulates a realistic hierarchical DNS resolution workflow:

```text
[UDP Client] ───(1. Query)───> [Local Server :5000] ───(Cache Miss?)───┐
                                                                      │
[UDP Client] <──(6. Return IP)── [Local Server :5000] <───┐            ▼
                                                          │   [Root Server :6000]
                                                          │            │ (Queries Auth)
                                                   (5. IP)│            ▼
                                                          └── [Auth Server :7000]

```

* **LocalServer (Port 5000):** Checks local `HashMap` cache for fast hits (e.g., `www.psau.edu.sa`). For misses, queries `RootServer` and caches the returned IP.


* **RootServer (Port 6000):** Intermediary router forwarding queries to `AuthServer`.


* **AuthServer (Port 7000):** Authoritative database holding global domain mappings.



---

### 🏆 Assignment 2: Non-Blocking Async RMI Grading System (`assignment02-async-rmi-grading`)

Implements non-blocking academic evaluation using **Java RMI** combined with **`CompletableFuture`**:

```text
[Main Thread]: Request sent to RMI server...
[Main Thread]: Performing concurrent task step 1
[Main Thread]: Performing concurrent task step 2
[Main Thread]: Performing concurrent task step 3
[Async Callback]: Response received from server -> Grade for 87.5 is: B+

```

---

## 💻 How to Run the Projects

### 1. General Execution (Terminal)

1. **Clone the repository:**
```bash
git clone [https://github.com/N7awaf/distributed-systems-labs.git](https://github.com/N7awaf/distributed-systems-labs.git)

```


2. **Navigate to the target lab directory (e.g., Lab 4):**
```bash
cd distributed-systems-labs/lab04-tcp-multithreaded-chat/src/main/java/

```


3. **Compile the source files:**
```bash
javac com/mycompany/dslab4/*.java

```



---

### 🚦 2. Execution Sequence & Server Dependencies

To prevent connection exceptions (`Connection Refused` or `NotBoundException`), always start backend servers before running the client:

* **Basic Client-Server Systems (Labs 1–4):**
* Start `Server` $\rightarrow$ Launch `Client`.




* **3-Tier & Multi-RMI Systems (Labs 5 & 7):**
* **Lab 5:** Start `DataServer` (Port 8888) $\rightarrow$ Start `ProcessServer` (Port 7777) $\rightarrow$ Launch `Client`.
* **Lab 7:** Start `SumServer` (Port 1900) $\rightarrow$ Start `TimeServer` (Port 2300) $\rightarrow$ Launch `SyncClient` / `AsyncClient`.


* **Hierarchical DNS System (Assignment 1):**
* Start `AuthServer` (Port 7000) $\rightarrow$ Start `RootServer` (Port 6000) $\rightarrow$ Start `LocalServer` (Port 5000) $\rightarrow$ Launch `Client`.




* **Async RMI System (Assignment 2):**
* Start `GradeServer` $\rightarrow$ Launch `AsyncGradeClient`.
```

```
