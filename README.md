# AI-Code-Helper: Java Programming AI Learning Assistant

This project is an AI learning assistant designed to help Java programmers:

* **Programming Learning Mentor:** Provides clear learning roadmap planning and personalized suggestions
* **Job Interview Assistant:** Covers resume optimization, interview skills, and analysis of frequently asked questions
* **Code Q&A Expert:** Provides real-time answers to programming technical questions and offers code examples

It is built using the **Java/Spring Boot** framework, leveraging **LangChain4j** for conversational capabilities and **MyBatis/MySQL** for persistent storage of chat memory.

## Key Features

* **Intelligent Assistance:** Provides quick and helpful answers for Java programming questions.
* **Persistent Memory:** Conversation history is stored in a MySQL database, allowing the assistant to remember past context even after the application restarts.
* **Extensible Architecture:** Easily switch or upgrade the underlying Large Language Model (LLM) thanks to the modular design provided by LangChain4j.

## Technologies Used

* **Backend:** Java, Spring Boot
* **AI Framework:** LangChain4j
* **Database:** MySQL
* **Persistence Layer:** MyBatis
* **LLM (Default):** Alibaba Qwen

---

## Project Setup and Installation

### Security and Configuration Best Practice

> **For privacy and security reasons, especially when working with sensitive API keys and database credentials:**
>
> 1.  Please copy the default `application.yml` file to `application-local.yml`.
> 2.  Configure all your local secrets (API keys, passwords) in `application-local.yml`.
> 3.  Ensure `application-local.yml` is added to your `.gitignore` file to prevent accidental upload to version control. All subsequent setup steps will refer to `application-local.yml`.

Follow these steps to set up and run the AI-Code-Helper service.

### 1. API Key Configuration (Model Access)

Due to cost considerations, this project defaults to using Alibaba's Tongyi Qianwen model.

* **Obtain API Key:** Visit the Alibaba Cloud console to apply for your API Key: [https://bailian.console.aliyun.com/?tab=model#/api-key](https://bailian.console.aliyun.com/?tab=model#/api-key); Visit [https://app.tavily.com/home](https://app.tavily.com/home) to apply for a Tavily API Key.
* **Configure:** Add the obtained API Key to your `application-local.yml` file.

**Note on Models:**
The Qwen model currently does not support multimodal conversations. If your budget allows, it is highly recommended to change the model in the source code to a more capable (and possibly more expensive) LLM supported by LangChain4j.

### 2. Database Setup (MySQL)

This project relies on MySQL for persistent chat memory.

* **Prepare Environment:** Ensure you have a running MySQL database server.
* **Initialize Database:** Execute the `init.sql` script provided in the project. This script will create the `ai_code_helper` database and the `chat_message_record` table.
* **Configure Access:** Update the database connection settings (url, username, password) in your `application-local.yml` file.

### 3. Developer Tooling (MyBatis Generator)

For developers who may need to modify the database schema:

* Ensure your MySQL connection configuration is also correctly set in `src/main/resources/generatorConfig.xml`.
* This configuration allows you to quickly use the MyBatis Generator plugin to regenerate the DAO layer (Mappers and Entities) after making changes to the database structure.

## Project Run

After completing the setup steps above, you can run the Spring Boot application using your IDE or by executing the JAR file.