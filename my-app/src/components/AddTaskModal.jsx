import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap"

const AddTaskModal = ({ show, handleClose, handleAdd }) => {
  const [taskName, setTaskName] = useState("");
  const [taskDescription, setTaskDescription] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [priority, setPriority] = useState("Medium");
  const [category, setCategory] = useState("");
  const [status, setStatus] = useState("Pending");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newTask = {
      taskName,
      taskDescription,
      dueDate,
      status,
      priority,
      category,
      companyId: sessionStorage.getItem("companyId"),
    };

    try {
      const response = await fetch(`http://localhost:8081/tasks/create`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newTask),
      });

      const data = await response.json();
      if (response.status === 201) {
        console.log("Course added successfully");
        handleAdd(data);
        handleClose();
      } else {
        console.error("Failed to add course");
      }
    } catch (error) {
      console.error("Error adding course:", error);
    }
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Add New Course</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="taskName">
            <Form.Label>Course Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter Course Name"
              value={taskName}
              onChange={(e) => setTaskName(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="taskDescription">
            <Form.Label>Course Description</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter Course description"
              value={taskDescription}
              onChange={(e) => setTaskDescription(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="dueDate">
            <Form.Label>Course Start Date</Form.Label>
            <Form.Control
              type="date"
              value={dueDate}
              onChange={(e) => setDueDate(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="status">
            <Form.Label>Status</Form.Label>
            <Form.Control
              as="select"
              value={status}
              onChange={(e) => setStatus(e.target.value)}
              required
            >
              <option>Ongoing</option>
              <option>Completed</option>
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="priority">
            <Form.Label>Difficulty</Form.Label>
            <Form.Control
              as="select"
              value={priority}
              onChange={(e) => setPriority(e.target.value)}
              required
            >
              <option>Low</option>
              <option>Medium</option>
              <option>High</option>
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="category">
            <Form.Label>Department</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter department"
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            />
          </Form.Group>
          
          <Button variant="primary" type="submit" className="mt-3">
            Add Task
          </Button>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default AddTaskModal;
