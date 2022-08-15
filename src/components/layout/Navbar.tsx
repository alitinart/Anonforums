import { useNavigate } from "react-router-dom";
import Icon from "../../assets/icon.svg";

export default function Navbar() {
  const nav = useNavigate();

  return (
    <div onClick={() => nav("/")} className="navbar">
      <img src={Icon} className="navbar__branding" alt="Anonforums" />
      <h1 className="navbar__title">ANONFORUMS</h1>
    </div>
  );
}
