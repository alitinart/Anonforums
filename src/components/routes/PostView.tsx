import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Post from "../../models/post.model";
import { requests } from "../../service/requests";

export default function PostView() {
  const [post, setPost] = useState<Post>();
  const { id }: any = useParams();

  useEffect(() => {
    const fetchData = async () => {
      setPost(await requests.posts.getPostById(id));
    };

    fetchData();
  }, []);

  return post ? (
    <div className="post">
      <h1 className="post__title">{post.title}</h1>
      <p className="post__author">Author: {post.author}</p>
      <p className="post__description">{post.description}</p>
    </div>
  ) : (
    <></>
  );
}
