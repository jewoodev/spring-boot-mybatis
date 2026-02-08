package data.spring.mybatis.adapter.`in`.member.request

import data.spring.mybatis.application.provided.member.dto.VfcCodeSendCommand


data class VfcCodeSendRequest(
    val memberId: Long
) {
    fun toCommand(): VfcCodeSendCommand = VfcCodeSendCommand(memberId)
}