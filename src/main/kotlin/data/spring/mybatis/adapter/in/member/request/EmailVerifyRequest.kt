package data.spring.mybatis.adapter.`in`.member.request

import data.spring.mybatis.application.provided.member.dto.EmailVerifyCommand

data class EmailVerifyRequest(
    val memberId: Long,
    val verificationCode: String
) {
    fun toCommand(): EmailVerifyCommand = EmailVerifyCommand(memberId, verificationCode)
}