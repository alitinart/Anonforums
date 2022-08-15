import { useEffect } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";

import "./App.css";
import "react-toastify/dist/ReactToastify.min.css";

import Navbar from "./components/layout/Navbar";
import CreatePost from "./components/routes/CreatePost";
import Home from "./components/routes/Home";
import { requests } from "./service/requests";
import CreatePostIcon from "./components/elements/createPostIcon";

export default function App() {
  useEffect(() => {
    const createUser = async () => {
      return await requests.users.createUser();
    };

    if (!localStorage.getItem("hasAccount")) {
      createUser();
      localStorage.setItem("hasAccount", "true");
    }
  }, []);

  return (
    <BrowserRouter>
      <Navbar />
      <ToastContainer />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/create-post" element={<CreatePost />}></Route>
      </Routes>
      <CreatePostIcon />
    </BrowserRouter>
  );
}
