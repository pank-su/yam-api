package model.account


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

enum class ExperimentStatus{
    @SerialName("default") DEFAULT,
    @SerialName("on") ON,
    @SerialName("on_lite") ON_LITE
}

@Serializable
data class Experiments(
    @SerialName("ABTestIds")
    val aBTestIds: String,
    @SerialName("androidAboutActivityComposeExperiment_Dev")
    val androidAboutActivityComposeExperimentDev: ExperimentStatus,
    @SerialName("androidAllTrackInfoBottomSheet_Dev")
    val androidAllTrackInfoBottomSheetDev: ExperimentStatus,
    @SerialName("androidAnimationCollectionFromNetwork")
    val androidAnimationCollectionFromNetwork: ExperimentStatus,
    @SerialName("androidApkUpdater")
    val androidApkUpdater: ExperimentStatus,
    @SerialName("androidAppsFlyerBillingTarget")
    val androidAppsFlyerBillingTarget: ExperimentStatus,
    @SerialName("androidArtistFamiliar")
    val androidArtistFamiliar: ExperimentStatus,
    @SerialName("androidArtistHeaderListenersNewUi")
    val androidArtistHeaderListenersNewUi: ExperimentStatus,
    @SerialName("androidArtistLinksBlock")
    val androidArtistLinksBlock: ExperimentStatus,
    @SerialName("androidArtistsConcertsUpper")
    val androidArtistsConcertsUpper: ExperimentStatus,
    @SerialName("AndroidAutoLikeFix_Dev")
    val androidAutoLikeFixDev: ExperimentStatus,
    @SerialName("androidAvailablePermanentTracks_Dev")
    val androidAvailablePermanentTracksDev: ExperimentStatus,
    @SerialName("androidBackendWaveButton")
    val androidBackendWaveButton: ExperimentStatus,
    @SerialName("androidBigPlayerArtists")
    val androidBigPlayerArtists: ExperimentStatus,
    @SerialName("androidBookmateOption")
    val androidBookmateOption: ExperimentStatus,
    @SerialName("AndroidCastOnboarding")
    val androidCastOnboarding: ExperimentStatus,
    @SerialName("AndroidCastOnboarding_NotReady")
    val androidCastOnboardingNotReady: ExperimentStatus,
    @SerialName("androidClipsSearch")
    val androidClipsSearch: ExperimentStatus,
    @SerialName("androidCoil_Dev")
    val androidCoilDev: ExperimentStatus,
    @SerialName("androidCollectionCarouselAnimation_Dev")
    val androidCollectionCarouselAnimationDev: ExperimentStatus,
    @SerialName("androidCollectionFavouritesNewLoading")
    val androidCollectionFavouritesNewLoading: ExperimentStatus,
    @SerialName("androidCollectionNewIcon")
    val androidCollectionNewIcon: ExperimentStatus,
    @SerialName("androidCollectionSplitPodcastsAudiobooks")
    val androidCollectionSplitPodcastsAudiobooks: ExperimentStatus,
    @SerialName("androidCompositeTrackIdParse")
    val androidCompositeTrackIdParse: ExperimentStatus,
    @SerialName("androidContextOnPause")
    val androidContextOnPause: ExperimentStatus,
    @SerialName("AndroidDeleteAccount")
    val androidDeleteAccount: ExperimentStatus,
    @SerialName("androidDisableOpkExperiment")
    val androidDisableOpkExperiment: ExperimentStatus,
    @SerialName("androidDisclaimerArtist")
    val androidDisclaimerArtist: ExperimentStatus,
    @SerialName("androidDisclaimerArtistForeignAgent")
    val androidDisclaimerArtistForeignAgent: ExperimentStatus,
    @SerialName("AndroidDiscountPrice")
    val androidDiscountPrice: ExperimentStatus,
    @SerialName("androidDivPaywall")
    val androidDivPaywall: ExperimentStatus,
    @SerialName("androidDownloadedPlaylistLogic_Dev")
    val androidDownloadedPlaylistLogicDev: ExperimentStatus,
    @SerialName("androidEOVRestoreSeedsBySessionExperiment")
    val androidEOVRestoreSeedsBySessionExperiment: ExperimentStatus,
    @SerialName("androidEmptyMediaButtonsExperiment")
    val androidEmptyMediaButtonsExperiment: ExperimentStatus,
    @SerialName("androidExplicitOverflowExperiment")
    val androidExplicitOverflowExperiment: ExperimentStatus,
    @SerialName("androidFavoritesCover")
    val androidFavoritesCover: ExperimentStatus,
    @SerialName("androidFixFlashingContextOnPause")
    val androidFixFlashingContextOnPause: ExperimentStatus,
    @SerialName("androidFixFlashingWaveSettingsOnYnison")
    val androidFixFlashingWaveSettingsOnYnison: ExperimentStatus,
    @SerialName("androidFixMediaLike")
    val androidFixMediaLike: ExperimentStatus,
    @SerialName("androidFixMediaSupportedCommands")
    val androidFixMediaSupportedCommands: ExperimentStatus,
    @SerialName("androidFixPlayAudioNegativeTotal")
    val androidFixPlayAudioNegativeTotal: ExperimentStatus,
    @SerialName("androidFixQueueStopRaceExperiment")
    val androidFixQueueStopRaceExperiment: ExperimentStatus,
    @SerialName("androidFixRadioFromGlagol")
    val androidFixRadioFromGlagol: ExperimentStatus,
    @SerialName("androidFixSessionImmediateRelease_Dev")
    val androidFixSessionImmediateReleaseDev: ExperimentStatus,
    @SerialName("androidFmRadio_Dev")
    val androidFmRadioDev: ExperimentStatus,
    @SerialName("androidFromWithSeeds")
    val androidFromWithSeeds: ExperimentStatus,
    @SerialName("AndroidFullScreen")
    val androidFullScreen: ExperimentStatus,
    @SerialName("androidFunnelTracking")
    val androidFunnelTracking: ExperimentStatus,
    @SerialName("androidGlagolAdjustProgress")
    val androidGlagolAdjustProgress: ExperimentStatus,
    @SerialName("androidHeaderLayoutTakesAllTheSpace")
    val androidHeaderLayoutTakesAllTheSpace: ExperimentStatus,
    @SerialName("androidHeaderSpecialBlockExperiment")
    val androidHeaderSpecialBlockExperiment: ExperimentStatus,
    @SerialName("androidHidePlaylistSizeExperiment")
    val androidHidePlaylistSizeExperiment: ExperimentStatus,
    @SerialName("androidHttp2")
    val androidHttp2: ExperimentStatus,
    @SerialName("androidInAppFiltration")
    val androidInAppFiltration: ExperimentStatus,
    @SerialName("androidInAppUpdates")
    val androidInAppUpdates: ExperimentStatus,
    @SerialName("androidInvisibleBrandedButtonOnTablet")
    val androidInvisibleBrandedButtonOnTablet: ExperimentStatus,
    @SerialName("androidKidsOptionPaywall")
    val androidKidsOptionPaywall: ExperimentStatus,
    @SerialName("AndroidKidsShtorka")
    val androidKidsShtorka: ExperimentStatus,
    @SerialName("androidLandingTabChart")
    val androidLandingTabChart: ExperimentStatus,
    @SerialName("androidLandingTabNewPlaylists")
    val androidLandingTabNewPlaylists: ExperimentStatus,
    @SerialName("androidLandingTabNewReleases")
    val androidLandingTabNewReleases: ExperimentStatus,
    @SerialName("androidLandingTimingsReporter_Dev")
    val androidLandingTimingsReporterDev: ExperimentStatus,
    @SerialName("androidLibraryPlaylistScreen")
    val androidLibraryPlaylistScreen: ExperimentStatus,
    @SerialName("AndroidLibrarySyncJobExperiment_Dev")
    val androidLibrarySyncJobExperimentDev: ExperimentStatus,
    @SerialName("androidLikeUnlikeRequest_Dev")
    val androidLikeUnlikeRequestDev: ExperimentStatus,
    @SerialName("androidLoadLocalUserPlaylist_Dev")
    val androidLoadLocalUserPlaylistDev: ExperimentStatus,
    @SerialName("androidLyricsDisableExperiment")
    val androidLyricsDisableExperiment: ExperimentStatus,
    @SerialName("androidLyricsDisabledFotTalkback")
    val androidLyricsDisabledFotTalkback: ExperimentStatus,
    @SerialName("androidMainScreenWindowLogger_Dev")
    val androidMainScreenWindowLoggerDev: ExperimentStatus,
    @SerialName("AndroidMediaPauseOnStopSignal")
    val androidMediaPauseOnStopSignal: ExperimentStatus,
    @SerialName("androidMiniPlayerUi")
    val androidMiniPlayerUi: ExperimentStatus,
    @SerialName("AndroidMixedLocalSearchAnalytics_Dev")
    val androidMixedLocalSearchAnalyticsDev: ExperimentStatus,
    @SerialName("AndroidMixedSearch")
    val androidMixedSearch: ExperimentStatus,
    @SerialName("androidModernPromoCodeScreen")
    val androidModernPromoCodeScreen: ExperimentStatus,
    @SerialName("androidModernfitRemoveCatchingException")
    val androidModernfitRemoveCatchingException: ExperimentStatus,
    @SerialName("androidMoveVibeAndDeleteListenBottomSheet_Dev")
    val androidMoveVibeAndDeleteListenBottomSheetDev: ExperimentStatus,
    @SerialName("androidMusicTabIcon")
    val androidMusicTabIcon: ExperimentStatus,
    @SerialName("androidMyMusicVibe")
    val androidMyMusicVibe: ExperimentStatus,
    @SerialName("androidMyShelf")
    val androidMyShelf: ExperimentStatus,
    @SerialName("androidMyShelfPromotionExperiment")
    val androidMyShelfPromotionExperiment: ExperimentStatus,
    @SerialName("androidMyVibeDefaultColor")
    val androidMyVibeDefaultColor: ExperimentStatus,
    @SerialName("androidMyVibeShortcut_Dev")
    val androidMyVibeShortcutDev: ExperimentStatus,
    @SerialName("androidNamesDiversityVibeSettingsExperiment")
    val androidNamesDiversityVibeSettingsExperiment: ExperimentStatus,
    @SerialName("androidNetworkMetricsExperiment_Dev")
    val androidNetworkMetricsExperimentDev: ExperimentStatus,
    @SerialName("androidNewArtistGallery")
    val androidNewArtistGallery: ExperimentStatus,
    @SerialName("androidNewBrandedButton")
    val androidNewBrandedButton: ExperimentStatus,
    @SerialName("androidNewCheckDeeplinkQueue_Dev")
    val androidNewCheckDeeplinkQueueDev: ExperimentStatus,
    @SerialName("androidNewDiscovery_Dev")
    val androidNewDiscoveryDev: ExperimentStatus,
    @SerialName("androidNewLandingMixes")
    val androidNewLandingMixes: ExperimentStatus,
    @SerialName("androidNewLiveHlsDownload_Dev")
    val androidNewLiveHlsDownloadDev: ExperimentStatus,
    @SerialName("androidNewMediaSessionCoverFallback_Dev")
    val androidNewMediaSessionCoverFallbackDev: ExperimentStatus,
    @SerialName("androidNewNetwork4_Dev")
    val androidNewNetwork4Dev: ExperimentStatus,
    @SerialName("androidNewNetworkCollectivePlaylists_Dev")
    val androidNewNetworkCollectivePlaylistsDev: ExperimentStatus,
    @SerialName("androidNewPlayerUIExperiment")
    val androidNewPlayerUIExperiment: ExperimentStatus,
    @SerialName("androidNewPlayerUIExperiment_Dev_NotReady")
    val androidNewPlayerUIExperimentDevNotReady: ExperimentStatus,
    @SerialName("androidNewPodcastsScreen_Dev")
    val androidNewPodcastsScreenDev: ExperimentStatus,
    @SerialName("androidNewRupOnboardingArtistsProvider")
    val androidNewRupOnboardingArtistsProvider: ExperimentStatus,
    @SerialName("androidNewSpecialHeaderExperiment")
    val androidNewSpecialHeaderExperiment: ExperimentStatus,
    @SerialName("androidNewTrackDownload_Dev")
    val androidNewTrackDownloadDev: ExperimentStatus,
    @SerialName("androidNewVibeButtonStateExperiment_Dev")
    val androidNewVibeButtonStateExperimentDev: ExperimentStatus,
    @SerialName("androidNewWizardDialogExperiment")
    val androidNewWizardDialogExperiment: ExperimentStatus,
    @SerialName("androidNoPlaybackStateChangedNotificationUpdate")
    val androidNoPlaybackStateChangedNotificationUpdate: ExperimentStatus,
    @SerialName("androidNotificationExplicitExperiment")
    val androidNotificationExplicitExperiment: ExperimentStatus,
    @SerialName("androidOffersConfig")
    val androidOffersConfig: ExperimentStatus,
    @SerialName("androidOfflineSwitcherView_Dev")
    val androidOfflineSwitcherViewDev: ExperimentStatus,
    @SerialName("androidOpenFavourites")
    val androidOpenFavourites: ExperimentStatus,
    @SerialName("androidPagerSensitivityExperiment")
    val androidPagerSensitivityExperiment: ExperimentStatus,
    @SerialName("androidPaywallProductTitleExperiment")
    val androidPaywallProductTitleExperiment: ExperimentStatus,
    @SerialName("androidPaywallPromoCodeVisibilityExperiment")
    val androidPaywallPromoCodeVisibilityExperiment: ExperimentStatus,
    @SerialName("androidPhotoPicker")
    val androidPhotoPicker: ExperimentStatus,
    @SerialName("androidPlayAudioFixBatchesExperiment")
    val androidPlayAudioFixBatchesExperiment: ExperimentStatus,
    @SerialName("androidPlayAudioNoService")
    val androidPlayAudioNoService: ExperimentStatus,
    @SerialName("androidPlayAudioRealId_Dev")
    val androidPlayAudioRealIdDev: ExperimentStatus,
    @SerialName("androidPlayButtonHiding")
    val androidPlayButtonHiding: ExperimentStatus,
    @SerialName("androidPlayVibeDeeplink")
    val androidPlayVibeDeeplink: ExperimentStatus,
    @SerialName("androidPlaybackRefactoring3_Dev")
    val androidPlaybackRefactoring3Dev: ExperimentStatus,
    @SerialName("androidPlaybackRefactoring4_Dev")
    val androidPlaybackRefactoring4Dev: ExperimentStatus,
    @SerialName("androidPlaybackStopOnLogout")
    val androidPlaybackStopOnLogout: ExperimentStatus,
    @SerialName("androidPlayerReport_Dev")
    val androidPlayerReportDev: ExperimentStatus,
    @SerialName("androidPlaylistWithLikesBlockLandingExperiment")
    val androidPlaylistWithLikesBlockLandingExperiment: ExperimentStatus,
    @SerialName("androidPlaylistWithLikesLandingCounter")
    val androidPlaylistWithLikesLandingCounter: ExperimentStatus,
    @SerialName("androidPlusLocale")
    val androidPlusLocale: ExperimentStatus,
    @SerialName("androidPreSaveExperiment")
    val androidPreSaveExperiment: ExperimentStatus,
    @SerialName("androidPurchaseBlock_Dev")
    val androidPurchaseBlockDev: ExperimentStatus,
    @SerialName("androidPurchaseFullscreen")
    val androidPurchaseFullscreen: ExperimentStatus,
    @SerialName("androidRadioAnalytics2")
    val androidRadioAnalytics2: ExperimentStatus,
    @SerialName("androidRadioAnalytics_Dev")
    val androidRadioAnalyticsDev: ExperimentStatus,
    @SerialName("androidRadioCatalogToKotlin")
    val androidRadioCatalogToKotlin: ExperimentStatus,
    @SerialName("androidRadioContinuedAutoflow")
    val androidRadioContinuedAutoflow: ExperimentStatus,
    @SerialName("AndroidRebrandingIcon_NotReady")
    val androidRebrandingIconNotReady: ExperimentStatus,
    @SerialName("androidRecentlyLanding")
    val androidRecentlyLanding: ExperimentStatus,
    @SerialName("androidRecentlyRemoveInCollection")
    val androidRecentlyRemoveInCollection: ExperimentStatus,
    @SerialName("androidRedAlerts2")
    val androidRedAlerts2: ExperimentStatus,
    @SerialName("androidReduceBundlePlaylistExperiment")
    val androidReduceBundlePlaylistExperiment: ExperimentStatus,
    @SerialName("androidReduceCallStationInfo_NotReady")
    val androidReduceCallStationInfoNotReady: ExperimentStatus,
    @SerialName("androidRefactoringDescriptionContextExperiment")
    val androidRefactoringDescriptionContextExperiment: ExperimentStatus,
    @SerialName("androidRefactoringQueueEventPlaybackEntity")
    val androidRefactoringQueueEventPlaybackEntity: ExperimentStatus,
    @SerialName("androidReloadPaywallOnResume")
    val androidReloadPaywallOnResume: ExperimentStatus,
    @SerialName("androidRemoveGsonFromStationDescriptor")
    val androidRemoveGsonFromStationDescriptor: ExperimentStatus,
    @SerialName("androidRemovePhoneSettingExperiment")
    val androidRemovePhoneSettingExperiment: ExperimentStatus,
    @SerialName("androidReporterReduceStackTraces_Dev")
    val androidReporterReduceStackTracesDev: ExperimentStatus,
    @SerialName("androidRetryRequests")
    val androidRetryRequests: ExperimentStatus,
    @SerialName("AndroidScaleAnimForMyVibeBtnAndVibrationResp")
    val androidScaleAnimForMyVibeBtnAndVibrationResp: ExperimentStatus,
    @SerialName("androidScrollFrameTracking")
    val androidScrollFrameTracking: ExperimentStatus,
    @SerialName("AndroidSdkSharedAuth")
    val androidSdkSharedAuth: ExperimentStatus,
    @SerialName("AndroidSdkSharedNetwork")
    val androidSdkSharedNetwork: ExperimentStatus,
    @SerialName("androidSealedRadio_Dev")
    val androidSealedRadioDev: ExperimentStatus,
    @SerialName("androidSearchEntityHelperChangeSource_Dev")
    val androidSearchEntityHelperChangeSourceDev: ExperimentStatus,
    @SerialName("androidSearchHeaders")
    val androidSearchHeaders: ExperimentStatus,
    @SerialName("androidSearchHeadersArtist")
    val androidSearchHeadersArtist: ExperimentStatus,
    @SerialName("androidSearchHeadersCollection")
    val androidSearchHeadersCollection: ExperimentStatus,
    @SerialName("androidSearchHistoryCompose")
    val androidSearchHistoryCompose: ExperimentStatus,
    @SerialName("androidSearchTracksAndAlbumsByArtistInCollection")
    val androidSearchTracksAndAlbumsByArtistInCollection: ExperimentStatus,
    @SerialName("AndroidSettingsInProfile")
    val androidSettingsInProfile: ExperimentStatus,
    @SerialName("androidShareButtonInCards")
    val androidShareButtonInCards: ExperimentStatus,
    @SerialName("androidShareInviteToFamily")
    val androidShareInviteToFamily: ExperimentStatus,
    @SerialName("androidShareToFacebookStories")
    val androidShareToFacebookStories: ExperimentStatus,
    @SerialName("androidShareVideoToInstagram")
    val androidShareVideoToInstagram: ExperimentStatus,
    @SerialName("androidSharedOkhttpClient")
    val androidSharedOkhttpClient: ExperimentStatus,
    @SerialName("androidShowBandlinkButton")
    val androidShowBandlinkButton: ExperimentStatus,
    @SerialName("androidSingleAfTrackEvents")
    val androidSingleAfTrackEvents: ExperimentStatus,
    @SerialName("androidSkipPurchaseRestore")
    val androidSkipPurchaseRestore: ExperimentStatus,
    @SerialName("androidSkipRestoredQueue")
    val androidSkipRestoredQueue: ExperimentStatus,
    @SerialName("androidSkipRestoredQueue_Dev")
    val androidSkipRestoredQueueDev: ExperimentStatus,
    @SerialName("androidSlides")
    val androidSlides: ExperimentStatus,
    @SerialName("androidSlidesScaleFixVideo")
    val androidSlidesScaleFixVideo: ExperimentStatus,
    @SerialName("AndroidSplashScreenAsActivity")
    val androidSplashScreenAsActivity: ExperimentStatus,
    @SerialName("androidStartFullScreen2")
    val androidStartFullScreen2: ExperimentStatus,
    @SerialName("androidStartWaveByTrackStartingFromTrack")
    val androidStartWaveByTrackStartingFromTrack: ExperimentStatus,
    @SerialName("AndroidSupportChat")
    val androidSupportChat: ExperimentStatus,
    @SerialName("androidTelegramWhatsappSharing")
    val androidTelegramWhatsappSharing: ExperimentStatus,
    @SerialName("androidTextNoWrappedExperiment_Dev")
    val androidTextNoWrappedExperimentDev: ExperimentStatus,
    @SerialName("androidTrackCacheViolationDiagnosis_Dev")
    val androidTrackCacheViolationDiagnosisDev: ExperimentStatus,
    @SerialName("androidTrackComplaint_NotReady")
    val androidTrackComplaintNotReady: ExperimentStatus,
    @SerialName("androidTrackInfoBottomSheet_Dev")
    val androidTrackInfoBottomSheetDev: ExperimentStatus,
    @SerialName("androidTracksCachePermanentFlag")
    val androidTracksCachePermanentFlag: ExperimentStatus,
    @SerialName("androidTransparentMiniPlayer")
    val androidTransparentMiniPlayer: ExperimentStatus,
    @SerialName("androidUniversalEntitiesScreen")
    val androidUniversalEntitiesScreen: ExperimentStatus,
    @SerialName("androidVibeButton")
    val androidVibeButton: ExperimentStatus,
    @SerialName("androidVibeButtonAlbum")
    val androidVibeButtonAlbum: ExperimentStatus,
    @SerialName("androidVibeButtonArtist")
    val androidVibeButtonArtist: ExperimentStatus,
    @SerialName("androidVibeButtonInBottomSheet")
    val androidVibeButtonInBottomSheet: ExperimentStatus,
    @SerialName("androidVibeButtonMetaTagGenre")
    val androidVibeButtonMetaTagGenre: ExperimentStatus,
    @SerialName("androidVibeInSearch")
    val androidVibeInSearch: ExperimentStatus,
    @SerialName("androidVibeTranslucentBottomSheet_Dev")
    val androidVibeTranslucentBottomSheetDev: ExperimentStatus,
    @SerialName("androidVideoClipCasting")
    val androidVideoClipCasting: ExperimentStatus,
    @SerialName("androidVideoClipChunkLoader_Dev")
    val androidVideoClipChunkLoaderDev: ExperimentStatus,
    @SerialName("androidVideoClipIconInMiniPlayer")
    val androidVideoClipIconInMiniPlayer: ExperimentStatus,
    @SerialName("androidVideoClipInfoBottomSheet")
    val androidVideoClipInfoBottomSheet: ExperimentStatus,
    @SerialName("androidVideoClipsChangePosition")
    val androidVideoClipsChangePosition: ExperimentStatus,
    @SerialName("androidVideoClipsOnArtistPage")
    val androidVideoClipsOnArtistPage: ExperimentStatus,
    @SerialName("androidVideoClipsQueue")
    val androidVideoClipsQueue: ExperimentStatus,
    @SerialName("androidVideoWaveLikesInMiniPlayer")
    val androidVideoWaveLikesInMiniPlayer: ExperimentStatus,
    @SerialName("androidWalMode_Dev")
    val androidWalModeDev: ExperimentStatus,
    @SerialName("androidWaveHeader")
    val androidWaveHeader: ExperimentStatus,
    @SerialName("androidYandexPlusUrlFix_Dev")
    val androidYandexPlusUrlFixDev: ExperimentStatus,
    @SerialName("AndroidYnisonChannelProviderFix_Dev")
    val androidYnisonChannelProviderFixDev: ExperimentStatus,
    @SerialName("AndroidYnisonLimitMode")
    val androidYnisonLimitMode: ExperimentStatus,
    @SerialName("AndroidYnisonLimitations_Dev")
    val androidYnisonLimitationsDev: ExperimentStatus,
    @SerialName("AndroidYnisonLogs_Dev")
    val androidYnisonLogsDev: ExperimentStatus,
    @SerialName("AndroidYnison_NotReady")
    val androidYnisonNotReady: ExperimentStatus,
    @SerialName("AndroidYnisonSharedPlayback_Dev")
    val androidYnisonSharedPlaybackDev: ExperimentStatus,
    @SerialName("AndroidYnisonStoreDevicesExperiment_Dev")
    val androidYnisonStoreDevicesExperimentDev: ExperimentStatus,
    @SerialName("androidYnisonTransferSessionId")
    val androidYnisonTransferSessionId: ExperimentStatus,
    @SerialName("AndroidYoungOffer")
    val androidYoungOffer: ExperimentStatus,
    @SerialName("BookmateValidationByDefault")
    val bookmateValidationByDefault: ExperimentStatus,
    @SerialName("boostConfigExperiment63ca5ce5868dce3221659e90")
    val boostConfigExperiment63ca5ce5868dce3221659e90: ExperimentStatus,
    @SerialName("boostConfigExperiment653bc19985739d0f815a1966")
    val boostConfigExperiment653bc19985739d0f815a1966: ExperimentStatus,
    @SerialName("boostConfigExperiment653bc1b885739d0f815a311e")
    val boostConfigExperiment653bc1b885739d0f815a311e: ExperimentStatus,
    @SerialName("boostConfigExperiment653bc1c785739d0f815a3122")
    val boostConfigExperiment653bc1c785739d0f815a3122: ExperimentStatus,
    @SerialName("boostConfigExperiment653bc1da85739d0f815a3126")
    val boostConfigExperiment653bc1da85739d0f815a3126: ExperimentStatus,
    @SerialName("boostConfigExperiment653bc1ed85739d0f815a312a")
    val boostConfigExperiment653bc1ed85739d0f815a312a: ExperimentStatus,
    @SerialName("boostConfigExperiment653bc1fd85739d0f815a312e")
    val boostConfigExperiment653bc1fd85739d0f815a312e: ExperimentStatus,
    @SerialName("boostConfigExperiment654e5856fdf76a26a2eb5c7c")
    val boostConfigExperiment654e5856fdf76a26a2eb5c7c: ExperimentStatus,
    @SerialName("boostConfigExperiment654e586cfdf76a26a2eb5c80")
    val boostConfigExperiment654e586cfdf76a26a2eb5c80: ExperimentStatus,
    @SerialName("boostConfigExperiment654e587efdf76a26a2eb5c84")
    val boostConfigExperiment654e587efdf76a26a2eb5c84: ExperimentStatus,
    @SerialName("boostConfigExperiment654e588efdf76a26a2eb5c88")
    val boostConfigExperiment654e588efdf76a26a2eb5c88: ExperimentStatus,
    @SerialName("disableListeningForFreemium")
    val disableListeningForFreemium: ExperimentStatus,
    @SerialName("disableLyricsForFreemium")
    val disableLyricsForFreemium: ExperimentStatus,
    @SerialName("disabledBlockIds")
    val disabledBlockIds: ExperimentStatus,
    @SerialName("expDistributionTest")
    val expDistributionTest: ExperimentStatus,
    @SerialName("forceClientCacheUpdate")
    val forceClientCacheUpdate: ExperimentStatus,
    @SerialName("GlagolTvPresetAndroidExperiment_Dev")
    val glagolTvPresetAndroidExperimentDev: ExperimentStatus,
    @SerialName("landingBanner")
    val landingBanner: ExperimentStatus,
    @SerialName("likeDislikeRequestFix_Dev")
    val likeDislikeRequestFixDev: ExperimentStatus,
    @SerialName("musicSearchRanking")
    val musicSearchRanking: ExperimentStatus,
    @SerialName("NativePaywallPromoCode")
    val nativePaywallPromoCode: ExperimentStatus,
    @SerialName("pinnedEditorialInPersonalPlaylistsBlock")
    val pinnedEditorialInPersonalPlaylistsBlock: ExperimentStatus,
    @SerialName("pinnedPersonalInPersonalPlaylistsBlock")
    val pinnedPersonalInPersonalPlaylistsBlock: ExperimentStatus,
    @SerialName("playlistBoostExperiment607289c805a7dd7ae28a8b04")
    val playlistBoostExperiment607289c805a7dd7ae28a8b04: ExperimentStatus,
    @SerialName("playlistBoostExperiment607289d405a7dd7ae28a8b07")
    val playlistBoostExperiment607289d405a7dd7ae28a8b07: ExperimentStatus,
    @SerialName("playlistBoostExperiment62b52e604607ba1e1cbf3747")
    val playlistBoostExperiment62b52e604607ba1e1cbf3747: ExperimentStatus,
    @SerialName("PlusPay")
    val plusPay: ExperimentStatus,
    @SerialName("PlusPay_NotReady")
    val plusPayNotReady: ExperimentStatus,
    @SerialName("pretrialPromoCode")
    val pretrialPromoCode: ExperimentStatus,
    @SerialName("PultBannerWithStationAndroid")
    val pultBannerWithStationAndroid: ExperimentStatus,
    @SerialName("PultForAllMiniplayerAndroid")
    val pultForAllMiniplayerAndroid: ExperimentStatus,
    @SerialName("PultPickerBannerAndroid")
    val pultPickerBannerAndroid: ExperimentStatus,
    @SerialName("PultPickerFos")
    val pultPickerFos: ExperimentStatus,
    @SerialName("Pultshufflerepeat_Dev")
    val pultshufflerepeatDev: ExperimentStatus,
    @SerialName("recentlyForAutoFromLanding")
    val recentlyForAutoFromLanding: ExperimentStatus,
    @SerialName("specialProjectExperiment_Dev")
    val specialProjectExperimentDev: ExperimentStatus,
    @SerialName("specialProjectHeaderExperiment_Dev")
    val specialProjectHeaderExperimentDev: ExperimentStatus,
    @SerialName("SyncLyricsAndroid")
    val syncLyricsAndroid: ExperimentStatus,
    @SerialName("yearRewindLanding")
    val yearRewindLanding: ExperimentStatus
): Result()