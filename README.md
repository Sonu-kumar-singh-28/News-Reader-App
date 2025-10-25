🗞️ News Reader App

News Reader App is a modern Android application designed to deliver real-time news, trending headlines, and personalized stories from around the world — all in a clean, responsive, and user-friendly interface. Built with a robust MVVM architecture, it ensures efficient performance, seamless data handling, and a delightful reading experience.

✨ Key Features

Real-Time News Updates: Fetch latest news from trusted sources using Retrofit and Coroutines.

Category-Based Exploration: Access articles in categories like Technology, Sports, Health, Business, and Entertainment.

Offline Reading: Save favorite articles with Room Database and read them without an internet connection.

Powerful Search: Find specific articles or topics instantly using coroutine-based API search.

Secure Authentication: Firebase Authentication for email/password login. Bookmarks are synced across devices.

Modern Navigation: Smooth multi-screen flow using Jetpack Navigation Component (Nav Graph).

🛠️ Technology Stack
Component	Technology	Purpose
Architecture	MVVM	Lifecycle-aware separation of UI and data for maintainable code
Networking	Retrofit + Coroutines	Asynchronous, structured network calls for fetching news
Local Storage	Room Database	Offline caching and persistent storage of articles
Dependency Injection	Hilt / Dagger	Centralized dependency management across the app
Navigation	Jetpack Navigation Component	Simplified and robust in-app navigation
Authentication	Firebase Auth	Secure user registration and login
API Source	NewsAPI.org / Custom REST Endpoint	Provides real-time news content

Focus is on a clean, scalable architecture and modern Android best practices.

🚀 Getting Started
Prerequisites

JDK 11 or higher

Android Studio (latest stable version)

Git

Gradle

Installation
git clone https://github.com/Sonu-Kumar-Singh-28/News-Reader-App.git
cd News-Reader-App

API Key Configuration

Get a free API key from NewsAPI.org
.

Add it to your local.properties or a constants file.

Firebase Setup

Create a Firebase project and enable Email/Password Authentication.

Download google-services.json and place it inside the /app folder.

Run the App

Open the project in Android Studio.

Sync Gradle files.

Choose an emulator or connect your device.

Run ▶️ to launch the News Reader App.

🤝 Contributing

We welcome contributions to improve the News Reader App:

Fork the repository.

Create a feature branch:

git checkout -b feature/new-feature


Commit your changes:

git commit -m "feat: added trending news section"


Push your branch:

git push origin feature/new-feature


Open a Pull Request for review.

📧 Contact

Sonu Kumar Singh
📩 Email: sonusinghsengar28@gmail.com

🔗 GitHub: News Reader App
