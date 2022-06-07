Change Log
==========
Version 3.6.0 *(2022-06-07)*
----------------------------
- Added `unit` to `StorageQuota`.
- Added `period` to `Periodic` and `Space`.
- Added `resetDate` to `Periodic`.

Version 3.5.0 *(2022-05-16)*
----------------------------
- Added new account types: Free, Starter, Standard, and Advanced.

Version 3.4.0 *(2022-05-03)*
----------------------------
- Renamed `FolderInteractions.edit` to `FolderInteractions.addRemoveVideos`.
- Added `FolderInteractions.delete` interaction.
- Added `FolderInteractions.editSettings` interaction.

Version 3.3.0 *(2022-04-28)*
----------------------------
- Added `Authenticator.checkSsoConnection` to check for SSO availability for an email.
- Added `Authenticator.createSsoAuthorizationUri(SsoConnection, String)` to check for SSO availability for an email.
- Deprecated `Authenticator.fetchSsoDomain` in favor of `Authenticator.checkSsoConnection`.
- Deprecated `Authenticator.createSsoAuthorizationUri(SsoDomain, String)` in favor of `Authenticator.createSsoAuthorizationUri(SsoConnection, String)`.
- Made `TeamEntity` and `TeamPermission` implement `Entity` interface.

Version 3.2.0 *(2022-04-22)*
----------------------------
- Added the following functions to`VimeoApiClient` which support team permission related operations, along with their respective supporting data classes: `fetchTeamPermissions`, `replaceTeamPermission`, `deleteTeamPermission`, `fetchPermissionPolicy`, `fetchPermissionPolicyList`

Version 3.1.0 *(2022-04-11)*
----------------------------
- Added the following interactions to `VideoInteractions`: `delete`, `edit`, `invite`.
- Added the following interactions to `FolderInteractions`: `deleteVideo`, `edit`, `invite`.

Version 3.0.0 *(2022-03-25)*
----------------------------
- Disables certificate pinning by default and removes the ability to pin to Vimeo's intermediate API certificate.
- Consumers of 2.x or 1.x versions of the library will need to upgrade to 3.x or manually disable certificate pinning before 3 March 2023, when the certificate pinned to in those versions expires. Failure to upgrade to 3.x or disable certificate pinning by that date will result in API requests failing.

Version 2.4.0 *(2022-03-08)*
----------------------------
- Added `id` to `Live`.

Version 2.3.0 *(2022-03-01)*
----------------------------
- Added `isPrivateToUser` and `accessGrant` to `Folder`.

Version 2.2.1 *(2022-02-17)*
----------------------------
- Fixed bug where `getMagistoTeamToken()` wasn't properly authenticating with the API`.

Version 2.2.0 *(2022-02-14)*
----------------------------
- Added `getMagistoTeamToken()` function to the `Authenticator`.

Version 2.1.0 *(2022-02-11)*
----------------------------
- Added `Authenticator.logOutLocally()` to log out the user locally in the library without removing their token on the server.

Version 2.0.10 *(2021-12-17)*
----------------------------
- Internal dependency updates 

Version 2.0.9 *(2021-11-02)*
----------------------------
- Added a few missing upload error codes to the ErrorCodeType enum. Note that the value of UPLOAD_QUOTA_COUNT_EXCEEDED has changed from 4102 to 4104. 4102 is now associated with UPLOAD_WEEKLY_QUOTA_SIZE_EXCEEDED.

Version 2.0.8 *(2021-09-29)*
----------------------------
- Added `ApiConstants.Parameters.FILTER_NO_LIVE` constant.

Version 2.0.7 *(2021-09-24)*
----------------------------
- Fixed incorrect value for `LiveStatusType.READY`.

Version 2.0.6 *(2021-08-24)*
----------------------------
- Added a `jitpack.yml` file to run Jitpack builds on JDK 11.

Version 2.0.5 *(2021-08-24)*
----------------------------
- Fixed incorrect values for `BillingPeriodType`.

Version 2.0.4 *(2021-08-10)*
----------------------------
- Added `minTierForMovie` field to `EditSession`.

Version 2.0.3 *(2021-08-10)*
----------------------------
- Improved support for user survey data by adding an `analyticsId` property to `SurveyQuestion`, `SurveyResponseChoice`, and `UserSegmentSurvey`. This property replaces any `id` properties the class originally had. 
- Ensured that `SurveyResponseChoice` has a guid-based `resourceKey` field that is tied to its `Entity.identifier`.
- Renamed the `SurveyQuestion.emojiTitle` property to `titleEmoji`.

Version 2.0.2 *(2021-08-06)*
----------------------------
- Changed the `Play.source` property to the correct type, from `List<VideoSourceFile>` to `VideoSourceFile`.

Version 2.0.1 *(2021-08-03)*
----------------------------
- Adding support for user survey data with new models and endpoint.

Version 2.0.0 *(2021-07-15)*
----------------------------
- Initial release!
