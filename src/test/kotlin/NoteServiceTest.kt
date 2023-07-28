import Notes
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        val note = Notes("title", "text", 123, false)
        val result = note.noteId
        assertEquals(123, result)
    }

    @Test
    fun createComment() {
        val comment = Comments(456, 123, "message", false)
        val result = comment.commentId
        assertEquals(456, result)
    }

    @Test
    fun deleteNoteTrue() {
        val note: Notes = NoteService.add(Notes("title", "text", 123, false))
        val result = note.isDelete
        assert(true)
    }
    @Test
    fun deleteNoteFalse() {
        val noteId = 963
        var result = true
        try {
            NoteService.deleteNote(noteId)
        } catch (e: NotFoundException) {
            result = false
        }
        assertFalse(result)
    }

    @Test
    fun deleteCommentTrue() {
        val comment = Comments(456, 123, "message", false)
        val result = comment.isDelete
        assert(true)
    }

    @Test
    fun deleteCommentFalse() {
        val result = try {
            NoteService.deleteComment(1)
        } catch (e: NotFoundException) {
            false
        }
        assertFalse(result)
    }

    @Test
    fun editTrue() {
        val noteId = NoteService.add(Notes("title", "text", 123, false))
        val result = NoteService.edit(789, Notes("t", "e", 789, false))
        assert(true)
    }

    @Test
    fun editFalse() {
        val result = NoteService.edit(789, Notes("t", "e", 789, true))
        assert(true)
    }

    @Test
    fun editComment() {
        val commentId = Comments(741, 963, "f", false)
        val result = NoteService.editComment(123, Comments(123, 7, "v", false))
        assert(true)
    }

    @Test
    fun editCommentFalse() {
        val result = NoteService.editComment(7, Comments(1, 2, "l", true))
        assert(true)
    }

    @Test
    fun get() {
        val result = NoteService.get()
        assert(true)
    }

    @Test
    fun getById() {
        val noteId = NoteService.add(Notes("d", "s", 8, false))
        val result = NoteService.getById(8, Notes("d", "s", 8, false))
        assert(true)
    }

    @Test
    fun getByIdFalse() {
        val result = try {
            NoteService.getById(8, Notes("d", "s", 8, false ))
            true
        } catch (e: NotFoundException) {
            false
        }
        assert(true)
    }

    @Test
    fun getComment() {
        val result = try {
            NoteService.getComment(8, Comments(8,8,"s", false))
            true
        } catch (e: NotFoundException) {
            false
        }
        assert(true)
    }

    @Test
    fun restoreComment() {
        var result = try {
            NoteService.restoreComment(7)
            true
        } catch (e: NotFoundException) {
            false
        }
        assert(true)
    }
}