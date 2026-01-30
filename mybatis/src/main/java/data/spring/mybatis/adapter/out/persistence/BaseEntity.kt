package data.spring.mybatis.adapter.out.persistence

import java.time.Clock
import java.time.LocalDateTime

fun clock(): Clock = Clock.systemDefaultZone()

abstract class BaseEntity {
    val createdAt: LocalDateTime = LocalDateTime.now(clock())
    val updatedAt: LocalDateTime = LocalDateTime.now(clock())
}