import axios from "axios";

const API_URL = "http://localhost:5000/api"

const apiClient = axios.create({
    baseURL: API_URL
})
export const getCategories = async () => {
    try {
        const response = await apiClient.get("/categories")
        return response.data
    } catch (err) {
        return Promise.reject(err.message);
    }
}

export const getNotes = async () => {
    try {
        const response = await apiClient.get("/notes")
        return response.data
    } catch (err) {
        return Promise.reject(err.message);
    }
}

export const createNote = async (note) => {
    try {
        const response = await apiClient.post("/notes", note)
        return response.data
    } catch (err) {
        return Promise.reject(err.message);
    }
}
export const deleteNote = async (id) => {
    try {
        const response = await apiClient.delete(`/notes/${id}`)
        return response.data
    } catch (err) {
        return Promise.reject(err.message);
    }
}