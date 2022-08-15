import axios from "axios";
import Post from "../models/post.model";

const API_URL = import.meta.env.VITE_API_URL;

export const requests = {
  posts: {
    getAllPosts: async () => {
      const { data } = await axios.get(`${API_URL}/posts`);
      return data;
    },
    getPostById: async (id: string) => {
      const { data } = await axios.get(`${API_URL}/posts/${id}`);
      return data;
    },
    createPost: async ({ title, description, author }: Post) => {
      const { data } = await axios.post(`${API_URL}/posts`, {
        title,
        description,
        author,
      });
      return data;
    },
  },
  users: {
    getAllUsers: async () => {
      const { data } = await axios.get(`${API_URL}/users`);
      return data;
    },
    findUserByName: async (name: string) => {
      const { data } = await axios.get(`${API_URL}/users?name=${name}`);
      return data;
    },
    createUser: async () => {
      const { data } = await axios.get(`${API_URL}/users/add`);
      return data;
    },
    findUserByAddress: async () => {
      const { data } = await axios.get(`${API_URL}/users/find/address`);
      return data;
    },
  },
};
