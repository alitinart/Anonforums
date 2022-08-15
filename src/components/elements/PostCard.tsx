import Post from "../../models/post.model";

export default function PostCard({ title, description, author }: Post) {
  return (
    <div className="post_card">
      <h1 className="post_card__title">{title}</h1>
      <p className="post_card__author">{author}</p>
    </div>
  );
}
