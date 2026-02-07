package data.spring.mybatis.domain.member

import kotlin.require

@JvmInline
value class Username(
    val value: String
) {
    init {
        require(value.length in  2..100) { "사용자 이름은 2자 이상 20자 이하여야 합니다." }
        require(value.matches(Regex("^[a-zA-Z0-9_-]+$"))) { "사용자 이름은 알파벳, 숫자, 언더스코어, 하이픈만 포함할 수 있습니다." }
    }
}
