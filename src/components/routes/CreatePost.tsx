import HCaptcha from "@hcaptcha/react-hcaptcha";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { requests } from "../../service/requests";

export default function CreatePost() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [token, setToken] = useState("");

  const nav = useNavigate();

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    if (!token) {
      return toast.error("Complete Captcha", {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    }

    const user = await requests.users.findUserByAddress();

    const res = await requests.posts.createPost({
      title,
      description,
      author: user.name,
    });
    if (res.error) {
      return toast.error(res.message, {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    }
    toast.success(res.message, {
      position: "top-center",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    nav("/");
  };

  return (
    <div className="create_post">
      <h1 className="create_post__title">Create Post</h1>
      <form
        className="create_post__form"
        onSubmit={(e) => {
          handleSubmit(e);
        }}
      >
        <input
          type="text"
          className="create_post__form__control"
          placeholder="Title"
          value={title}
          onChange={(e) => {
            setTitle(e.target.value);
          }}
          required
        />
        <textarea
          className="create_post__form__control"
          placeholder="Description"
          rows={10}
          value={description}
          onChange={(e) => {
            setDescription(e.target.value);
          }}
          required
        ></textarea>
        <HCaptcha
          sitekey="9176018e-f6aa-4bbc-8ac6-c2e700bb34bd"
          onVerify={(token, ekey) => {
            setToken(token);
          }}
        />
        <button className="create_post__form__button" type="submit">
          Post
        </button>
      </form>
    </div>
  );
}
