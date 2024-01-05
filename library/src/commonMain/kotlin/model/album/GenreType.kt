package model.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// hard-coded genres
@Serializable
enum class GenreType {
    @SerialName("rock")
    Rock,

    @SerialName("alternative")
    Alternative,

    @SerialName("pop")
    Pop,

    @SerialName("films")
    Films,

    @SerialName("rusrap")
    RusRap,

    @SerialName("local-indie")
    LocalIndie,

    @SerialName("indie")
    Indie,

    @SerialName("rap")
    Rap,

    @SerialName("foreignrap")
    ForeignRap,

    @SerialName("ska")
    SKA,

    @SerialName("punk")
    Punk,

    @SerialName("postpunk")
    PostPunk,

    @SerialName("rusrock")
    RussianRock,

    @SerialName("animated")
    Animated,

    @SerialName("soundtrack")
    SoundTrack,

    @SerialName("rusbards")
    Rusbards,

    @SerialName("ruspop")
    RusPop,

    @SerialName("dance")
    Dance,

    @SerialName("podcasts")
    Podcasts,

    @SerialName("edmgenre")
    EDMGenre,

    @SerialName("breakbeatgenre")
    BreakbeatGenre,

    @SerialName("ukrrock")
    UkrainianRock,

    @SerialName("experimental")
    Experimental,

    @SerialName("electronics")
    Electronics,

    @SerialName("industrial")
    Industrial,

    @SerialName("rusestrada")
    RussianEstrada,

    @SerialName("numetal")
    NuMetal,

    @SerialName("metalcoregenre")
    MetalcoreGenre,

    @SerialName("metal")
    Metal,

    @SerialName("classicmetal")
    ClassicMetal,

    @SerialName("all")
    AllGenres,

    @SerialName("disco")
    Disco,

    @SerialName("kpop")
    KoreanPop,

    @SerialName("turkishpop")
    TurkishPop,

    @SerialName("uzbekpop")
    UzbekPop,

    @SerialName("japanesepop")
    JapanesePop,

    @SerialName("israelipop")
    IsraeliPop,

    @SerialName("azerbaijanpop")
    AzerbaijaniPop,

    @SerialName("hyperpopgenre")
    HyperpopGenre,

    @SerialName("mizrahi")
    Mizrahi,

    @SerialName("arabicpop")
    ArabicPop,

    @SerialName("egyptianpop")
    EgyptianPop,

    @SerialName("khaleejipop")
    KhaleejiPop,

    @SerialName("allrock")
    AllRock,

    @SerialName("rnr")
    RockNRoll,

    @SerialName("prog")
    ProgressiveRock,

    @SerialName("postrock")
    PostRock,

    @SerialName("newwave")
    NewWave,

    @SerialName("folkrock")
    FolkRock,

    @SerialName("stonerrock")
    StonerRock,

    @SerialName("hardrock")
    HardRock,

    @SerialName("turkishrock")
    TurkishRock,

    @SerialName("israelirock")
    IsraeliRock,

    @SerialName("progmetal")
    ProgressiveMetal,

    @SerialName("epicmetal")
    EpicMetal,

    @SerialName("folkmetal")
    FolkMetal,

    @SerialName("gothicmetal")
    GothicMetal,

    @SerialName("sludgemetal")
    SludgeMetal,

    @SerialName("postmetal")
    PostMetal,

    @SerialName("thrashmetal")
    ThrashMetal,

    @SerialName("deathmetal")
    DeathMetal,

    @SerialName("blackmetal")
    BlackMetal,

    @SerialName("doommetal")
    DoomMetal,

    @SerialName("alternativemetal")
    AlternativeMetal,

    @SerialName("posthardcore")
    PostHardcore,

    @SerialName("hardcore")
    Hardcore,

    @SerialName("turkishalternative")
    TurkishAlternative,

    @SerialName("techno")
    Techno,

    @SerialName("house")
    House,

    @SerialName("trance")
    Trance,

    @SerialName("bassgenre")
    BassGenre,

    @SerialName("dubstep")
    Dubstep,

    @SerialName("triphopgenre")
    TripHopGenre,

    @SerialName("ukgaragegenre")
    UKGarageGenre,

    @SerialName("idmgenre")
    IDMGenre,

    @SerialName("ambientgenre")
    AmbientGenre,

    @SerialName("newage")
    NewAge,

    @SerialName("lounge")
    Lounge,

    @SerialName("phonkgenre")
    PhonkGenre,

    @SerialName("turkishrap")
    TurkishRap,

    @SerialName("israelirap")
    IsraeliRap,

    @SerialName("arabicrap")
    ArabicRap,

    @SerialName("rnb")
    RnB,

    @SerialName("soul")
    Soul,

    @SerialName("funk")
    Funk,

    @SerialName("jazz")
    Jazz,

    @SerialName("tradjazz")
    TraditionalJazz,

    @SerialName("conjazz")
    ContemporaryJazz,

    @SerialName("bebopgenre")
    BebopGenre,

    @SerialName("vocaljazz")
    VocalJazz,

    @SerialName("smoothjazz")
    SmoothJazz,

    @SerialName("bigbands")
    BigBands,

    @SerialName("blues")
    Blues,

    @SerialName("reggae")
    Reggae,

    @SerialName("reggaeton")
    Reggaeton,

    @SerialName("dub")
    Dub,

    @SerialName("folk")
    Folk,

    @SerialName("rusfolk")
    RussianFolk,

    @SerialName("tatar")
    Tatar,

    @SerialName("celtic")
    Celtic,

    @SerialName("balkan")
    Balkan,

    @SerialName("eurofolk")
    EuropeanFolk,

    @SerialName("jewish")
    Jewish,

    @SerialName("eastern")
    Eastern,

    @SerialName("african")
    African,

    @SerialName("latinfolk")
    LatinFolk,

    @SerialName("amerfolk")
    AmericanFolk,

    @SerialName("romances")
    Romances,

    @SerialName("argentinetango")
    ArgentineTango,

    @SerialName("armenian")
    Armenian,

    @SerialName("georgian")
    Georgian,

    @SerialName("azerbaijani")
    Azerbaijani,

    @SerialName("caucasian")
    Caucasian,

    @SerialName("turkishclassical")
    TurkishClassical,

    @SerialName("arabesquemusic")
    ArabesqueMusic,

    @SerialName("turkishfolk")
    TurkishFolk,

    @SerialName("estrada")
    Estrada,

    @SerialName("shanson")
    Shanson,

    @SerialName("country")
    Country,

    @SerialName("tvseries")
    TVSeries,

    @SerialName("videogame")
    VideoGame,

    @SerialName("animemusic")
    AnimeMusic,

    @SerialName("musical")
    Musical,

    @SerialName("bollywood")
    Bollywood,

    @SerialName("relax")
    Relax,

    @SerialName("meditation")
    Meditation,

    @SerialName("children")
    Children,

    @SerialName("naturesounds")
    NatureSounds,

    @SerialName("bard")
    Bard,

    @SerialName("foreignbard")
    ForeignBard,

    @SerialName("forchildren")
    ForChildren,

    @SerialName("lullaby")
    Lullaby,

    @SerialName("poemsforchildren")
    PoemsForChildren,

    @SerialName("classicalmusic")
    ClassicalMusic,

    @SerialName("vocal")
    Vocal,

    @SerialName("modern")
    Modern,

    @SerialName("classical")
    Classical,

    @SerialName("classicalmasterpieces")
    ClassicalMasterpieces,

    @SerialName("fiction")
    Fiction,

    @SerialName("romancenovel")
    RomanceNovel,

    @SerialName("historicalfiction")
    HistoricalFiction,

    @SerialName("sciencefiction")
    ScienceFiction,

    @SerialName("fantasyliterature")
    FantasyLiterature,

    @SerialName("actionandadventure")
    ActionAndAdventure,

    @SerialName("crimeandmystery")
    CrimeAndMystery,

    @SerialName("horrorandthrillers")
    HorrorAndThrillers,

    @SerialName("dramaliterature")
    DramaLiterature,

    @SerialName("childrensliterature")
    ChildrensLiterature,

    @SerialName("nonfictionliterature")
    NonfictionLiterature,

    @SerialName("community")
    Community,

    @SerialName("work")
    Work,

    @SerialName("technologies")
    Technologies,

    @SerialName("hls")
    HigherEducationAndLearning,

    @SerialName("selfdevelopment")
    SelfDevelopment,

    @SerialName("psychologyandphilosophy")
    PsychologyAndPhilosophy,

    @SerialName("religionandspirituality")
    ReligionAndSpirituality,

    @SerialName("popularsciencebooks")
    PopularScienceBooks,

    @SerialName("booksnotinrussian")
    BooksNotInRussian,

    @SerialName("audiobooksinenglish")
    AudiobooksInEnglish,

    @SerialName("audiobooks")
    Audiobooks,

    @SerialName("folkgenre")
    FolkGenre,

    @SerialName("shelatgenre")
    ShelatGenre,

    @SerialName("islamicgenre")
    IslamicGenre,

    @SerialName("other")
    Other,

    @SerialName("sport")
    Sport,

    @SerialName("holiday")
    Holiday,


    @SerialName("social-tracks")
    SocialTracks,

    @SerialName("dnb")
    DrumAndBase,

    @SerialName("soviet")
    Soviet,

    @SerialName("fairytales")
    FairyTales

}