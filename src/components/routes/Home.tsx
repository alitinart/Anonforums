import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Post from "../../models/post.model";
import { requests } from "../../service/requests";
import PostCard from "../elements/PostCard";

export default function Home() {
  const [posts, setPosts] = useState<Post[]>([]);
  const [isReqDone, setIsReqDone] = useState(false);

  useEffect(() => {
    const fetchPosts = async () => {
      const res = await requests.posts.getAllPosts();
      setPosts(res.reverse());
      setIsReqDone(true);
    };

    fetchPosts();
  }, []);

  let today: any = new Date();
  let dd = String(today.getDate()).padStart(2, "0");
  let mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
  let yyyy = today.getFullYear();

  today = dd + "/" + mm + "/" + yyyy;

  return (
    <div className="home">
      <h1 className="home__date">{today}</h1>
      <hr />
      <div className="home__latest_posts">
        {posts.length > 0 ? (
          posts.map((post) => {
            return <PostCard key={post.id} {...post} />;
          })
        ) : isReqDone ? (
          <h1 className="home__no_posts">
            No posts at the moment... <Link to={"/create-post"}>Post Here</Link>
          </h1>
        ) : (
          <div className="lds-roller">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
          </div>
        )}
      </div>
    </div>
  );
}
