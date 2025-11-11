package su.pank.yamapi.model.cover

/**
 * Интерфейс для сущностей, имеющих обложки/изображения.
 * Предоставляет утилитарные методы для формирования URL изображений с подстановкой размера.
 */
interface WithCover {
    /**
     * Формирует полный URL изображения из URI с подстановкой размера.
     *
     * @param uri URI изображения с плейсхолдером "%%"
     * @param size Размер изображения
     * @return Полный URL изображения или null, если URI отсутствует
     */
    fun buildImageUrl(uri: String?, size: CoverSize): String? {
        return if (uri != null) {
            "https://${uri.replace("%%", size.toString())}"
        } else {
            null
        }
    }
}

