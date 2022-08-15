import { useNavigate } from "react-router-dom";
import Post from "../../models/post.model";

export default function PostCard({ id, title, description, author }: Post) {
  const nav = useNavigate();

  return (
    <div className="post_card" onClick={() => nav(`/post/${id}`)}>
      <h1 className="post_card__title">{title}</h1>
      <p className="post_card__author">
        Author: <strong>{author}</strong>
      </p>
    </div>
  );
}
