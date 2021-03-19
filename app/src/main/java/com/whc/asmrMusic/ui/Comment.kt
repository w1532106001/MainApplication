package com.whc.asmrMusic.ui

class Comment {
    var id = 0
    var userId = 0
    var userName = ""
    var likeNum = 0
    var commentText = ""
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (userName != other.userName) return false
        if (likeNum != other.likeNum) return false
        if (commentText != other.commentText) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + userId
        result = 31 * result + userName.hashCode()
        result = 31 * result + likeNum
        result = 31 * result + commentText.hashCode()
        return result
    }


}