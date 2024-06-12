package track.model.downloadInfo

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("download-info")
internal data class DownloadInfoXML(
    @XmlElement(true)
    val host: String,
    @XmlElement(true)
    val path: String,
    @XmlElement(true)
    val ts: String,
    @XmlElement(true)
    val region: Int,
    @XmlElement(true)
    val s: String
)
