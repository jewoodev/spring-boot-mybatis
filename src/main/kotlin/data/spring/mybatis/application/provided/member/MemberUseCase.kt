package data.spring.mybatis.application.provided.member

import data.spring.mybatis.domain.member.Member
import data.spring.mybatis.application.provided.member.dto.EmailVerifyCommand
import data.spring.mybatis.application.provided.member.dto.MemberCreateCommand
import data.spring.mybatis.application.provided.member.dto.VfcCodeSendCommand

interface MemberUseCase {
    fun register(createCommand: MemberCreateCommand)

    fun sendVerificationCode(codeSendCommand: VfcCodeSendCommand)
    fun verify(verifyCommand: EmailVerifyCommand)

    fun findById(memberId: Long): Member?
    fun findAll(): List<Member?>

    fun changePassword(member: Member, newPassword: String)

    fun leave(member: Member)

    fun deleteAll(): Int
}