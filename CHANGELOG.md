Change Log
==========
Version 2.0.4 *(2021-08-10)*
----------------------------
- Added `minTierForMovie` field to `EditSession`.

Version 2.0.3 *(2021-08-10)*
----------------------------
- Improved support for user survey data by adding an `analyticsId` property to `SurveyQuestion`, `SurveyResponseChoice`, and `UserSegmentSurvey`. This property replaces any `id` properties the class originally had. 
- Ensured that `SurveyResponseChoice` has a guid-based `resourceId` field that is tied to its `Entity.identifier`.
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
