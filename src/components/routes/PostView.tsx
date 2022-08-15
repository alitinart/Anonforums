import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { toast } from "react-toastify";
import Post from "../../models/post.model";
import { requests } from "../../service/requests";

export default function PostView() {
  const [post, setPost] = useState<Post>();
  const [content, setContent] = useState("");
  const { id }: any = useParams();

  useEffect(() => {
    const fetchData = async () => {
      setPost(await requests.posts.getPostById(id));
    };

    fetchData();
  }, []);

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    const user = await requests.users.findUserByAddress();
    const res = await requests.posts.addComment(id, {
      content,
      author: user.name,
    });
    setPost(await requests.posts.getPostById(id));
    setContent("");
    toast.success(res.message);
  };

  return post ? (
    <div className="post">
      <h1 className="post__title">{post.title}</h1>
      <p className="post__author">
        Author: <strong>{post.author}</strong>
      </p>
      <pre className="post__description">{post.description}</pre>
      <div className="comments">
        <h1 className="comments__title">Comments</h1>
        <form className="comments__form" onSubmit={(e) => handleSubmit(e)}>
          <input
            className="comments__form__box"
            placeholder="Comment"
            type={"text"}
            value={content}
            onChange={(e) => setContent(e.target.value)}
            required
          />
          <button className="comments__form__button" type="submit">
            Submit
          </button>
        </form>
        {post.comments?.reverse().map((comment) => {
          return (
            <div className="comment">
              <h1 className="comment__content">{comment.content}</h1>
              <p className="comment__author">
                Author: <strong>{comment.author}</strong>
              </p>
            </div>
          );
        })}
      </div>
    </div>
  ) : (
    <></>
  );
}
