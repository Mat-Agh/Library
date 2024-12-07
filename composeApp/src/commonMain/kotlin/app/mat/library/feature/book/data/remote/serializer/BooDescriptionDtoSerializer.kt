package app.mat.library.feature.book.data.remote.serializer

import app.mat.library.feature.book.data.remote.dto.BookDescriptionDto
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object BookDescriptionDtoSerializer: KSerializer<BookDescriptionDto> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        serialName = BookDescriptionDto::class.simpleName!!
    )

    override fun deserialize(decoder: Decoder): BookDescriptionDto {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BookDescriptionDto) {
        TODO("Not yet implemented")
    }

}