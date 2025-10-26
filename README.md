🗞️ News Reader App

A modern Android application delivering breaking news, trending headlines, and personalized stories — all in one place. Built with a clean MVVM architecture for seamless data handling, efficient performance, and a delightful reading experience.

✨ Key Features

Real-Time News Updates: Fetch trending news from trusted sources using Retrofit + Coroutines.

Category-Based News: Explore stories in Technology, Sports, Health, Business, and Entertainment.

Offline Reading: Save articles for offline access with Room Database caching.

Powerful Search: Quickly find topics or articles using coroutine-based API search.

Firebase Authentication: Secure email/password login, account registration, and bookmark sync.

Modern Navigation: Smooth multi-screen flow with Jetpack Navigation Component.

Bookmark Sync Across Devices: Saved articles are linked to your account seamlessly.

🛠️ Technology Stack
Component	Technology / Library	Purpose / Role
Architecture	MVVM	Clean separation of UI and data, lifecycle-aware components for robust apps.
Networking	Retrofit + Kotlin Coroutines	Efficient asynchronous API calls and structured concurrency for fetching real-time news.
Local Storage	Room Database	Caching articles for offline reading and data persistence.
Dependency Injection	Hilt / Dagger	Centralized, scalable dependency management across the app.
Navigation	Jetpack Navigation Component	Handles multi-screen navigation smoothly using a single Navigation Graph.
Authentication	Firebase Auth	Secure user registration, login, and account management.
API Source	NewsAPI.org / Custom REST APIs	Provides real-time, categorized news data for the app.
UI Components	Jetpack Compose / Material UI	Modern, responsive, and declarative UI design for Android.
Image Loading	Coil / Glide	Efficient image caching and rendering in articles and lists.
Background Work	WorkManager	Handles offline sync, scheduled updates, and background tasks reliably.
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

Get your free API key from NewsAPI.org

Add it to local.properties or a constants file in the project.

Firebase Setup:

Create a Firebase project and enable Authentication (Email/Password).

Download google-services.json and place it inside the /app folder.

Run the App:

Open the project in Android Studio.

Sync Gradle files.

Choose an emulator or connect your device.

Hit Run ▶️ to launch the News Reader App.

🤝 Contributing

We welcome all contributions!

Fork the repository.

Create a new feature branch:

git checkout -b feature/new-feature


Commit your changes:

git commit -m 'feat: added trending news section'


Push your branch:

git push origin feature/new-feature


Open a Pull Request for review.

📧 Contact

👤 Sonu Kumar Singh
📩 Email: sonusinghsengar28@gmail.com

🔗 GitHub: https://github.com/Sonu-Kumar-Singh-28
