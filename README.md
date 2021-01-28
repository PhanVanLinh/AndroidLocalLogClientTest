# AndroidLocalLogClient

Client app for server app at
https://github.com/PhanVanLinh/AndroidLocalLogServer

Communicate to server by IPC (Inter Process Communication) via AIDL (Android Interface Define Language)
Security by the same signature permission `android:protectionLevel="signature"`

## Feature
- Send normal log / error log (`object Parcelable`) to server