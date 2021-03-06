USE [master]
GO
/****** Object:  Database [Lab03DB_BachMinhDuc]    Script Date: 05/19/2016 02:43:33 ******/
CREATE DATABASE [Lab03DB_BachMinhDuc] ON  PRIMARY 
( NAME = N'Lab03DB_BachMinhDuc', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\Lab03DB_BachMinhDuc.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Lab03DB_BachMinhDuc_log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\Lab03DB_BachMinhDuc_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Lab03DB_BachMinhDuc].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET ANSI_NULLS OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET ANSI_PADDING OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET ARITHABORT OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET  DISABLE_BROKER
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET  READ_WRITE
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET RECOVERY SIMPLE
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET  MULTI_USER
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [Lab03DB_BachMinhDuc] SET DB_CHAINING OFF
GO
USE [Lab03DB_BachMinhDuc]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 05/19/2016 02:43:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[id] [nchar](10) NOT NULL,
	[name] [nvarchar](50) NULL,
	[unit] [nvarchar](50) NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_tblProducts] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Products] ([id], [name], [unit], [price]) VALUES (N'1         ', N'Pepsi', N'chai', 1)
INSERT [dbo].[Products] ([id], [name], [unit], [price]) VALUES (N'3         ', N'COla', N'lon', 1.2)
INSERT [dbo].[Products] ([id], [name], [unit], [price]) VALUES (N'4         ', N'ruou de', N'xi', 0.5)
/****** Object:  Table [dbo].[Orders]    Script Date: 05/19/2016 02:43:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Orders] ([id]) VALUES (N'duc       ')
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 05/19/2016 02:43:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[orderId] [nchar](10) NULL,
	[productId] [nchar](10) NULL,
	[quantity] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'1         ', 1)
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'1         ', 11)
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'3         ', 6)
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'4         ', 7)
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'1         ', 11)
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'3         ', 6)
INSERT [dbo].[OrderDetails] ([orderId], [productId], [quantity]) VALUES (N'duc       ', N'4         ', 22)
/****** Object:  ForeignKey [FK_OrderDetails_Orders]    Script Date: 05/19/2016 02:43:34 ******/
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders] FOREIGN KEY([orderId])
REFERENCES [dbo].[Orders] ([id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders]
GO
/****** Object:  ForeignKey [FK_OrderDetails_Products]    Script Date: 05/19/2016 02:43:34 ******/
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([productId])
REFERENCES [dbo].[Products] ([id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
GO
