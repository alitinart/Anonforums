export default interface Post {
  id?: string;
  title: string;
  description: string;
  author: string;
  comments?: { content: string; replies: []; author: string }[];
}
