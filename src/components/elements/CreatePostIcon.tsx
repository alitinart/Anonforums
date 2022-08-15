import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function CreatePostIcon() {
  const nav = useNavigate();

  return (
    <i
      onClick={() => {
        nav("/create-post");
      }}
      className="bi bi-plus create-post-icon"
    ></i>
  );
}
