import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import './globals.css'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'UPT Online Cafeteria',
  description: 'Make a reservation for your next meal.',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <head>
        <link rel='icon' href='/favicon.ico'></link>
      </head>
      <body className={inter.className}>
        {children}
      </body>
    </html>
  )
}
