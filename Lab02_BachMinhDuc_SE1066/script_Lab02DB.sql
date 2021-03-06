CREATE DATABASE [Lab02DB_BachMinhDuc_SE1066]
GO
USE [Lab02DB_BachMinhDuc_SE1066]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 05/14/2016 20:04:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[Code] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Description] [nvarchar](50) NULL,
	[Price] [float] NULL,
	[Category] [nvarchar](50) NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[Code] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Product] ([Code], [Name], [Description], [Price], [Category]) VALUES (N'b0001', N'Coca Cola', N'<img src="images/sample.jpg">', 0.5, N'Beverage')
INSERT [dbo].[Product] ([Code], [Name], [Description], [Price], [Category]) VALUES (N'b0012', N'Pepsi', N'<img src="images/sample2.jpg">', 0.5, N'Beverage')
