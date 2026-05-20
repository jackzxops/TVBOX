# App Update Flow

The app now checks the managed update endpoint instead of the original upstream release endpoint.

## Endpoint

Default placeholder:

```text
https://your-domain/update.json
```

When you have your own domain or static hosting, replace `Github.URL` in:

```text
app/src/main/java/com/fongmi/android/tv/utils/Github.java
```

## update.json format

```json
{
  "name": "TVBox Restart v2",
  "code": 101,
  "desc": "更新说明：修复问题，优化源更新提示。",
  "apk": "https://your-domain/apk/TVBox-Restart-v2.apk"
}
```

## Behavior

- App startup and manual version check request `update.json`.
- If `code` is greater than current `BuildConfig.VERSION_CODE`, the app shows an update dialog.
- User confirms, app downloads the APK and opens the Android installer.
- Source update and App update are separate flows.
