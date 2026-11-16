-- Script tạo Database và bảng [User] cho SQL Server

CREATE DATABASE [UserAuthDB];
GO

USE [UserAuthDB];
GO

-- Tạo bảng [User]
CREATE TABLE [User] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [email] VARCHAR(100) NOT NULL,
    [username] VARCHAR(50) NOT NULL UNIQUE,
    [fullname] NVARCHAR(100) NOT NULL,
    [password] VARCHAR(100) NOT NULL,
    [avatar] VARCHAR(255) NULL,
    [roleid] INT NOT NULL DEFAULT 5, -- 1: Admin, 2: Manager, 5: Member/User
    [phone] VARCHAR(20) NULL,
    [createddate] DATE NULL
);
GO

-- Chèn dữ liệu mẫu kiểm thử các quyền
-- Mật khẩu ở ví dụ này để dạng plain text để kiểm thử (trong thực tế nên băm bằng BCrypt/SHA-256)
INSERT INTO [User] ([email], [username], [fullname], [password], [avatar], [roleid], [phone], [createddate])
VALUES 
('admin@example.com', 'admin', N'Quản Trị Viên', '123456', NULL, 1, '0987654321', GETDATE()),
('manager@example.com', 'manager', N'Quản Lý', '123456', NULL, 2, '0912345678', GETDATE()),
('user@example.com', 'user01', N'Nguyễn Văn A', '123456', NULL, 5, '0909090909', GETDATE());
GO

-- Kiểm tra dữ liệu
SELECT * FROM [User];
GO

