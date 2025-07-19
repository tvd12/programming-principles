import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [apiData, setApiData] = useState<string>('')

  const handleClick = async () => {
    setCount((prev) => prev + 1)

    try {
      const res = await fetch('http://localhost:8080/api/v1/cors/data')
      if (!res.ok) throw new Error('API call failed')

      const data = await res.json()
      setApiData(JSON.stringify(data))
    } catch (error: any) {
      console.error(error)
      setApiData('Lỗi khi gọi API')
    }
  }

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={handleClick}>
          count is {count}
        </button>
        <label style={{ display: 'block', marginTop: '10px' }}>
          Current Count: {count}
        </label>
        <label style={{ display: 'block', marginTop: '10px' }}>
          API Response: {apiData}
        </label>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
