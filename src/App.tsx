import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './app.css'
import Navbar from './components/layout/Navbar'
import Home from './components/routes/Home'

export default function App() {
  return (
    <BrowserRouter>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home />}></Route>
      </Routes>
    </BrowserRouter>
  )
}