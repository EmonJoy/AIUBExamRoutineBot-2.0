# AIUB Exam Routine Bot 2.0

A simple Telegram bot built using **Java 22.0.1** that helps students of **AIUB (American International University-Bangladesh)** find their exam routine quickly by subject code or name. just search on telegram "@coder22bot" and use it 

## Features

- Find exam date and slot instantly by typing subject code or name
- Lightweight and fast response
- Simple structure using plain text data (`Data.txt`)
- Docker support for easy deployment
- No database required – works offline with file-based data

## How It Works

- Users send a subject name  via Telegram
- The bot checks `pdf file
- ` and replies with the exam date and time
- Example:
    ```
    User: oop
    Bot: Subject: OOP
         Date: 03/05/2025
         Time: Slot B (2:00 PM - 4:00 PM)
    ```

## Technologies Used

- Java 22.0.1
- Telegram Bot API
- Maven
- Docker

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/EmonJoy/AIUBExamRoutineBot-2.0.git
cd AIUBExamRoutineBot-2.0
