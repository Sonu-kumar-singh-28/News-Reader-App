# 🗞️ News-Reader-App
 

Welcome to the repository for News Reader App, a modern Android application designed to deliver breaking news, trending headlines, and personalized stories from around the world — all in one place. Built with a clean MVVM architecture, this app ensures seamless data handling, efficient performance, and a delightful reading experience.

✨ Key Features

Stay updated with the latest happenings using a powerful, intuitive interface:

Real-Time News Updates: Fetches the latest and trending news from trusted sources using REST APIs and Retrofit.

Category-Based News: Explore stories from categories like Technology, Sports, Health, Business, and Entertainment.

Offline Reading (Room Database): Save your favorite articles for later and read them even without an internet connection.

Search Functionality: Quickly find specific articles or topics using coroutine-based API search.

Firebase Authentication:

Register: Create a new account with your email and password.

Login: Securely sign in with Firebase Auth.

Bookmark Sync: Your saved articles stay linked with your account.

Modern Navigation: Powered by the Jetpack Navigation Component (Nav Graph) for a smooth, multi-screen flow.

🛠️ Technology Stack
Component	Technology	Role
Architecture	MVVM	Clean, lifecycle-aware data and UI separation
Networking	Retrofit + Coroutines	Fetching news asynchronously with structured concurrency
Local Storage	Room Database	Caching and offline article storage
Dependency Injection	Hilt / Dagger	Managing app-wide dependencies
Navigation	Jetpack Navigation Component	Simplified in-app navigation
Authentication	Firebase Auth	Secure user login and registration
API Source	NewsAPI.org or custom REST endpoint	Provides real-time news data
🚀 Getting Started
Prerequisites

JDK 11 or higher

Android Studio (latest stable version)

Git

Gradle

Installation & Setup

Clone the repository:

git clone https://github.com/Sonu-Kumar-Singh-28/News-Reader-App.git
cd News-Reader-App


API Key Configuration:

Get your free API key from https://newsapi.org
.

Add it to your local.properties or constants file.

Firebase Setup:

Create a Firebase project and enable Authentication (Email/Password).

Download your google-services.json file and place it inside the /app folder.

Run the App:

Open the project in Android Studio.

Sync Gradle files.

Choose an emulator or connect your device.

Hit Run ▶️ to launch News Reader App.

🤝 Contributing

We welcome all contributions to make News Reader App even better!

Fork this repository.

Create a new feature branch (git checkout -b feature/new-feature).

Commit your changes (git commit -m 'feat: added trending news section').

Push your branch (git push origin feature/new-feature).

Open a Pull Request for review.

📧 Contact

Sonu Kumar Singh
📩 Email: sonusinghsengar28@gmail.com

🔗 Project Link: https://github.com/Sonu-Kumar-Singh-28/News-Reader-App
