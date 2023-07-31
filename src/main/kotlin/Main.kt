data class Notes(
    val title: String,
    val text: String,
    val noteId: Int,
    var isDelete: Boolean = false,
)

data class Comments(
    val commentId: Int,
    val noteId: Int,
    val message: String,
    var isDelete: Boolean = false,
)

class NotFoundException(message: String) : RuntimeException(message)


fun main(args: Array<String>) {

    val note1 = NoteService.add(Notes("title1", "text1", 123, false))
    val note2 = NoteService.add(Notes("title2", "text2", 456, false))

    val comment1 = NoteService.createComment(Comments(789, 123, "message1", false))
    val comment2 = NoteService.createComment(Comments(234, 456, "message2", false))


    println("add notes 1,2")
    println(NoteService.add(note1))

    println("create comments 1,2")
    println(NoteService.createComment(comment1))

    println("delete note1")
    println(NoteService.deleteNote(123))

    println("delete comment1")
    println(NoteService.deleteComment(789))

    println("edit note2")
    println(NoteService.edit(456, note2))

    println("edit comment2")
    println(NoteService.editComment(234, comment2))

    println("get comments")
    println (NoteService.get())
}


object NoteService {
    private var notes = mutableListOf<Notes>()
    private var comments = mutableListOf<Comments>()

    fun add(note: Notes): Notes {
        notes += note.copy()
        return notes.last()
    }

    fun createComment(comment: Comments): Comments {
        for (note in notes) {
            if (comment.noteId == note.noteId && !note.isDelete) {
                comments += comment.copy()
                return comments.last()
            }
        }
        throw NotFoundException("you can`t comment this note")

    }

    fun deleteNote(noteId: Int): Boolean {
        for (note in notes) {
            if (note.noteId == noteId && !note.isDelete) {
                note.isDelete = true
                return true
            }
        }
        throw NotFoundException("note is already deleted")
    }

    fun deleteComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId && !comment.isDelete) {
                comment.isDelete = true
                return true
            }
        }
        throw NotFoundException("comment is already deleted")
    }

    fun edit(noteId: Int, note: Notes): Boolean {
        for ((index, note1) in notes.withIndex()) {
            if (note.noteId == noteId && !note.isDelete)
                notes[index] == note.copy(noteId = notes[index].noteId)
            return true
        }
        return false
    }

    fun editComment(commentId: Int, comment: Comments): Boolean {
        for ((index, comment1) in comments.withIndex()) {
            if (comment.commentId == commentId && !comment.isDelete)
                comments[index] == comment.copy(commentId = comments[index].commentId)
            return true
        }
        return false
    }

    fun get(): MutableList<Notes> {
        return notes
    }


    fun getById(noteId: Int, note: Notes): Notes {
        for (note in notes) {
            if (note.noteId == noteId && !note.isDelete)
                return note
        }
        throw NotFoundException("error")
    }

   fun getComment(noteId: Int): MutableList<Comments> {
        for (comment in comments) {
            if (!comment.isDelete && comment.noteId == noteId)
                return comments
        }
        throw NotFoundException("deleted comment")
    }

    fun restoreComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId && comment.isDelete)
                comment.isDelete = true
            return true
        }
        throw NotFoundException("access to comment denied")
    }


}